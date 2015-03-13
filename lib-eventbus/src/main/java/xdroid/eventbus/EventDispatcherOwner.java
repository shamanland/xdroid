package xdroid.eventbus;

import android.os.Bundle;

import xdroid.core.ContextOwner;
import xdroid.customservice.CustomServiceResolver;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface EventDispatcherOwner extends ContextOwner, CustomServiceResolver {
    boolean DEFAULT_RAISE_INITIAL_EVENT_WHEN_RE_CREATING = true;
    boolean DEFAULT_RAISE_SAVED_EVENT_WHEN_RE_CREATING = true;
    boolean DEFAULT_IGNORE_FINISHED_EVENT = false;
    boolean DEFAULT_IGNORE_ACTIVITY_RESULT_CANCELLED = true;

    int getEventDispatcherXmlId();

    Options getEventDispatcherOptions();

    Bundle extractInitialEvent();

    static class Options {
        public boolean raiseInitialEventWhenReCreating = DEFAULT_RAISE_INITIAL_EVENT_WHEN_RE_CREATING;
        public boolean raiseSavedEventWhenReCreating = DEFAULT_RAISE_SAVED_EVENT_WHEN_RE_CREATING;
        public boolean ignoreFinishedEvent = DEFAULT_IGNORE_FINISHED_EVENT;
        public boolean ignoreActivityResultCancelled = DEFAULT_IGNORE_ACTIVITY_RESULT_CANCELLED;
        boolean activityReCreating;
        boolean activityResultInvoked;
        boolean activityResultHasData;
    }
}
