package xdroid.eventbus;

import android.os.Bundle;

import static xdroid.core.ObjectUtils.notNull;


/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class EventDispatcherHelper {
    public static void onCreate(EventDispatcherOwner owner, Bundle state) {
        if (owner.getEventDispatcherXmlId() != 0) {
            final EventDispatcher dispatcher = EventDispatcherInflater.getInstance().inflate(owner.getContext(), owner.getEventDispatcherXmlId());
            owner.putCustomService(EventDispatcher.class.getName(), dispatcher);

            if (state == null || owner.raiseInitialEventWhenReCreating()) {
                Bundle event = owner.extractInitialEvent();
                if (event != null) {
                    dispatcher.onNewEvent(EventBus.getEventId(event), event);
                }
            }

            if (owner.allowKeepLastEvent()) {
                Object keeper = new KeepLastEventDispatcher(dispatcher, state);
                owner.putCustomService(EventDispatcher.class.getName(), keeper);
                owner.putCustomService(KeepLastEventDispatcher.class.getName(), keeper);
            }
        }
    }

    public static void onSaveInstanceState(EventDispatcherOwner owner, Bundle state) {
        Object dispatcher = owner.getCustomService(KeepLastEventDispatcher.class.getName());
        if (dispatcher instanceof KeepLastEventDispatcher) {
            ((KeepLastEventDispatcher) dispatcher).onSaveInstanceState(state);
        }
    }

    public static void resetLastEvent(EventDispatcherOwner owner) {
        Object dispatcher = owner.getCustomService(KeepLastEventDispatcher.class.getName());
        if (dispatcher instanceof KeepLastEventDispatcher) {
            ((KeepLastEventDispatcher) dispatcher).reset();
        }
    }

    private EventDispatcherHelper() {
        // disallow public access
    }

    static class KeepLastEventDispatcher implements EventDispatcher {
        private static final String KEY_LAST = KeepLastEventDispatcher.class.getName() + ".last";

        private final EventDispatcher mBase;
        private Bundle mLast;

        public KeepLastEventDispatcher(EventDispatcher base, Bundle state) {
            mBase = notNull(base);

            if (state != null) {
                mLast = state.getBundle(KEY_LAST);
                if (mLast != null) {
                    mBase.onNewEvent(EventBus.getEventId(mLast), mLast);
                }
            }
        }

        public void reset() {
            mLast = null;
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
