package xdroid.customservice;

import java.util.Map;

import xdroid.collections.Prototypes;

import static xdroid.core.Objects.notNull;

public class CustomServices implements CustomServiceResolver {
    private final CustomServiceResolver mParentResolver;
    private final Map<String, Object> mCustomServices;

    public CustomServices(CustomServiceResolver parentResolver) {
        mParentResolver = parentResolver;
        mCustomServices = Prototypes.newHashMap();
    }

    @Override
    public void putCustomService(String name, Object instance) {
        mCustomServices.put(notNull(name), instance);
    }

    @Override
    public Object getCustomService(String name) {
        return mCustomServices.get(name);
    }

    @Override
    public CustomServiceResolver getParentResolver() {
        return mParentResolver;
    }
}
