package xdroid.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import xdroid.core.BundleBuilder;

import static xdroid.core.ObjectUtils.notNull;


/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class EventDispatcherHelper {
    public static void onCreate(EventDispatcherOwner owner, Bundle state) {
        EventDispatcherOwner.Options options = owner.getEventDispatcherOptions();

        options.activityReCreating = state != null;
        options.activityResultInvoked = false;

        if (owner.getEventDispatcherXmlId() != 0) {
            final EventDispatcher dispatcher = EventDispatcherInflater.getInstance().inflate(owner.getContext(), owner.getEventDispatcherXmlId());
            owner.putCustomService(EventDispatcher.class.getName(), dispatcher);

            if (state == null || options.raiseInitialEventWhenReCreating) {
                Bundle event = owner.extractInitialEvent();
                if (event != null) {
                    dispatcher.onNewEvent(EventBus.getEventId(event), event);
                }
            }

            if (options.raiseSavedEventWhenReCreating) {
                Object keeper = new KeepLastEventDispatcher(dispatcher, state);
                owner.putCustomService(EventDispatcher.class.getName(), keeper);
                owner.putCustomService(KeepLastEventDispatcher.class.getName(), keeper);
            }
        }
    }

    public static void onResume(EventDispatcherOwner owner) {
        EventDispatcherOwner.Options options = owner.getEventDispatcherOptions();

        Object dispatcher = owner.getCustomService(KeepLastEventDispatcher.class.getName());

        if (options.activityResultInvoked) {
            if (!options.activityResultHasData) {
                if (dispatcher instanceof KeepLastEventDispatcher) {
                    ((KeepLastEventDispatcher) dispatcher).resetIfForwarder();
                }
            }
        }

        if (options.activityReCreating) {
            options.activityReCreating = false;

            if (dispatcher instanceof KeepLastEventDispatcher) {
                ((KeepLastEventDispatcher) dispatcher).raiseLastEvent();
            }
        }
    }

    public static void onSaveInstanceState(EventDispatcherOwner owner, Bundle state) {
        Object dispatcher = owner.getCustomService(KeepLastEventDispatcher.class.getName());
        if (dispatcher instanceof KeepLastEventDispatcher) {
            ((KeepLastEventDispatcher) dispatcher).onSaveInstanceState(state);
        }
    }

    public static boolean onActivityResult(EventDispatcherOwner owner, int requestCode, int resultCode, Intent data) {
        EventDispatcherOwner.Options options = owner.getEventDispatcherOptions();

        options.activityResultInvoked = true;
        options.activityResultHasData = data != null;

        Bundle event = EventBus.extract(data);
        if (event != null) {
            //noinspection ConstantConditions
            if (EventFinish.class.getName().equals(data.getAction())) {
                if (options.ignoreFinishedEvent) {
                    return false;
                }
            } else if (resultCode == Activity.RESULT_CANCELED && options.ignoreActivityResultCancelled) {
                return false;
            }

            return EventBus.send(owner.getContext(), event);
        }

        return false;
    }

    private EventDispatcherHelper() {
        // disallow public access
    }

    static class KeepLastEventDispatcher implements EventDispatcher, Internal {
        private static final String KEY_LAST = KeepLastEventDispatcher.class.getName() + ".last";

        private final EventDispatcher mBase;
        private Bundle mLast;

        public KeepLastEventDispatcher(EventDispatcher base, Bundle state) {
            mBase = notNull(base);

            if (state != null) {
                mLast = state.getBundle(KEY_LAST);
            }
        }

        public void raiseLastEvent() {
            if (mLast != null) {
                mBase.onNewEvent(EventBus.getEventId(mLast), mLast);
            }
        }

        public void resetIfForwarder() {
            if (mLast != null) {
                if (mBase.onNewEvent(HELPER_EVENT_IS_FORWARDER, new BundleBuilder(ORIGINAL_EVENT_ID, EventBus.getEventId(mLast)).get())) {
                    mLast = null;
                }
            }
        }

        @Override
        public boolean onNewEvent(int eventId, Bundle event) {
            mLast = EventBus.prepare(eventId, event);
            return mBase.onNewEvent(eventId, event);
        }

        public void onSaveInstanceState(Bundle state) {
            state.putBundle(KEY_LAST, mLast);
        }
    }
}
