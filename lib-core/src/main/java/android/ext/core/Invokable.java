package android.ext.core;

/**
 * Alternative to {@link Runnable}.
 *
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface Invokable {
    Object invoke(Object... args);
}
