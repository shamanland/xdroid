package xdroid.collections;

import java.util.HashMap;
import java.util.TreeMap;

public final class Prototypes {
    private Prototypes() {
        // disallow public access
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayListExt<T> newArrayList() {
        return ArrayListHolder.INSTANCE.clone();
    }

    @SuppressWarnings("unchecked")
    public static <T> LinkedListExt<T> newLinkedList() {
        return LinkedListHolder.INSTANCE.clone();
    }

    @SuppressWarnings("unchecked")
    public static <K, V> HashMap<K, V> newHashMap() {
        return (HashMap) HashMapHolder.INSTANCE.clone();
    }

    @SuppressWarnings("unchecked")
    public static <K, V> TreeMap<K, V> newTreeMap() {
        return (TreeMap) TreeMapHolder.INSTANCE.clone();
    }

    static final class ArrayListHolder {
        static final ArrayListExt INSTANCE = new ArrayListExt(2);
    }

    static final class LinkedListHolder {
        static final LinkedListExt INSTANCE = new LinkedListExt();
    }

    static final class HashMapHolder {
        static final HashMap INSTANCE = new HashMap(4);
    }

    static final class TreeMapHolder {
        static final TreeMap INSTANCE = new TreeMap();
    }
}
