package android.ext.example;

import android.ext.app.ActivityExt;
import android.os.Bundle;

public class ExampleActivity extends ActivityExt {
    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.a_example);

        if (state == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ExampleListFragment())
                    .commit();
        }
    }
}
