package xdroid.eventbus;

import xdroid.core.ContextOwner;
import xdroid.customservice.CustomServiceResolver;
import android.os.Bundle;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface EventDispatcherOwner extends ContextOwner, CustomServiceResolver {
    int getEventDispatcherXmlId();

    boolean allowKeepLastEvent();

    Bundle extractInitialEvent();
}
