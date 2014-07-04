package xdroid.collections;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface FactoryArg2<T, A1, A2> {
    T create(A1 arg1, A2 arg2);
}
