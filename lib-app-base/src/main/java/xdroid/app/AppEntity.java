package xdroid.app;

import xdroid.core.ActivityStarter;
import xdroid.core.ContextOwner;
import xdroid.customservice.CustomServiceResolver;

public interface AppEntity extends ActivityStarter, ContextOwner, CustomServiceResolver {
    /**
     * Gets FragmentManager instance.
     */
    Object getFm();

    /**
     * Gets ActionBar instance.
     */
    Object getAb();
}
