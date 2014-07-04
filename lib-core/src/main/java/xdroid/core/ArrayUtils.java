package xdroid.core;

import static xdroid.core.BuildConfig.SNAPSHOT;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class ArrayUtils {
    private ArrayUtils() {
        // disallow public access
    }

    public static <T> T[] fromObjects(T... args) {
        return args;
    }

    public static boolean[] from(boolean... args) {
        return args;
    }

    public static byte[] from(byte... args) {
        return args;
    }

    public static char[] from(char... args) {
        return args;
    }

    public static short[] from(short... args) {
        return args;
    }

    public static int[] from(int... args) {
        return args;
    }

    public static long[] from(long... args) {
        return args;
    }

    public static float[] from(float... args) {
        return args;
    }

    public static double[] from(double... args) {
        return args;
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(boolean[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(char[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(short[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(double[] array) {
        return array == null || array.length == 0;
    }

    public static <T> T[] notEmpty(T[] array) {
        if (SNAPSHOT) {
            if (isEmpty(array)) {
                throw new IllegalArgumentException();
            }
        }

        return array;
    }

    public static boolean[] notEmpty(boolean[] array) {
        if (SNAPSHOT) {
            if (isEmpty(array)) {
                throw new IllegalArgumentException();
            }
        }

        return array;
    }

    public static byte[] notEmpty(byte[] array) {
        if (SNAPSHOT) {
            if (isEmpty(array)) {
                throw new IllegalArgumentException();
            }
        }

        return array;
    }

    public static char[] notEmpty(char[] array) {
        if (SNAPSHOT) {
            if (isEmpty(array)) {
                throw new IllegalArgumentException();
            }
        }

        return array;
    }

    public static short[] notEmpty(short[] array) {
        if (SNAPSHOT) {
            if (isEmpty(array)) {
                throw new IllegalArgumentException();
            }
        }

        return array;
    }

    public static int[] notEmpty(int[] array) {
        if (SNAPSHOT) {
            if (isEmpty(array)) {
                throw new IllegalArgumentException();
            }
        }

        return array;
    }

    public static long[] notEmpty(long[] array) {
        if (SNAPSHOT) {
            if (isEmpty(array)) {
                throw new IllegalArgumentException();
            }
        }

        return array;
    }

    public static float[] notEmpty(float[] array) {
        if (SNAPSHOT) {
            if (isEmpty(array)) {
                throw new IllegalArgumentException();
            }
        }

        return array;
    }

    public static double[] notEmpty(double[] array) {
        if (SNAPSHOT) {
            if (isEmpty(array)) {
                throw new IllegalArgumentException();
            }
        }

        return array;
    }
}
