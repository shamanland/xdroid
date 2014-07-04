package xdroid.customservice;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface CustomServiceResolver {
    void putCustomService(String name, Object instance);

    Object getCustomService(String name);

    CustomServiceResolver getParentResolver();
}
