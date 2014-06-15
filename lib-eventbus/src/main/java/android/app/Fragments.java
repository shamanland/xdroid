package android.app;

public final class Fragments {
    private Fragments() {
        // disallow public access
    }

    public static boolean isInBackStack(Fragment fragment) {
        return false; // fragment.isInBackStack();
    }
}
