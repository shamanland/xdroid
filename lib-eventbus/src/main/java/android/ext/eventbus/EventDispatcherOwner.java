package android.ext.eventbus;

import android.ext.core.ContextOwner;
import android.ext.customservice.CustomServiceResolver;
import android.os.Bundle;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface EventDispatcherOwner extends ContextOwner, CustomServiceResolver {
    int getEventDispatcherXmlId();

    Bundle extractInitialEvent();
}
