package xdroid.core;

import java.util.Arrays;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class ObjectUtils {
    private ObjectUtils() {
        // disallow public access
    }

    public static <T> T notNull(T object) {
        if (object == null) {
            throw new NullPointerException();
        }

        return object;
    }

    public static boolean isEmpty(CharSequence text) {
        return text == null || text.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence text) {
        return !isEmpty(text);
    }

    public static CharSequence notEmpty(CharSequence text) {
        if (isEmpty(text)) {
            throw new IllegalArgumentException();
        }

        return text;
    }

    public static boolean isEmpty(String text) {
        return text == null || text.length() == 0;
    }

    public static boolean isNotEmpty(String text) {
        return !isEmpty(text);
    }

    public static String notEmpty(String text) {
        if (isEmpty(text)) {
            throw new IllegalArgumentException();
        }

        return text;
    }

    public static <T> T[] asArrayObjects(T... args) {
        return args;
    }

    public static boolean[] asArray(boolean... args) {
        return args;
    }

    public static byte[] asArray(byte... args) {
        return args;
    }

    public static char[] asArray(char... args) {
        return args;
    }

    public static short[] asArray(short... args) {
        return args;
    }

    public static int[] asArray(int... args) {
        return args;
    }

    public static long[] asArray(long... args) {
        return args;
    }

    public static float[] asArray(float... args) {
        return args;
    }

    public static double[] asArray(double... args) {
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

    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(boolean[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(byte[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(char[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(short[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(int[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(long[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(float[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(double[] array) {
        return !isEmpty(array);
    }

    public static <T> T[] notEmpty(T[] array) {
        if (isEmpty(array)) {
            throw new IllegalArgumentException(Arrays.toString(array));
        }

        return array;
    }

    public static boolean[] notEmpty(boolean[] array) {
        if (isEmpty(array)) {
            throw new IllegalArgumentException(Arrays.toString(array));
        }

        return array;
    }

    public static byte[] notEmpty(byte[] array) {
        if (isEmpty(array)) {
            throw new IllegalArgumentException(Arrays.toString(array));
        }

        return array;
    }

    public static char[] notEmpty(char[] array) {
        if (isEmpty(array)) {
            throw new IllegalArgumentException(Arrays.toString(array));
        }

        return array;
    }

    public static short[] notEmpty(short[] array) {
        if (isEmpty(array)) {
            throw new IllegalArgumentException(Arrays.toString(array));
        }

        return array;
    }

    public static int[] notEmpty(int[] array) {
        if (isEmpty(array)) {
            throw new IllegalArgumentException(Arrays.toString(array));
        }

        return array;
    }

    public static long[] notEmpty(long[] array) {
        if (isEmpty(array)) {
            throw new IllegalArgumentException(Arrays.toString(array));
        }

        return array;
    }

    public static float[] notEmpty(float[] array) {
        if (isEmpty(array)) {
            throw new IllegalArgumentException(Arrays.toString(array));
        }

        return array;
    }

    public static double[] notEmpty(double[] array) {
        if (isEmpty(array)) {
            throw new IllegalArgumentException(Arrays.toString(array));
        }

        return array;
    }
}
