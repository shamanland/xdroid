package xdroid.eventbus;

import android.os.Bundle;

import xdroid.core.ContextOwner;
import xdroid.customservice.CustomServiceResolver;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface EventDispatcherOwner extends ContextOwner, CustomServiceResolver {
    int getEventDispatcherXmlId();

    boolean allowKeepLastEvent();

    boolean raiseInitialEventWhenReCreating();

    Bundle extractInitialEvent();
}
