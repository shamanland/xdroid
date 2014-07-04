package xdroid.core.test;

import xdroid.core.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class ObjectsTest {
    @Test(expected = NullPointerException.class)
    public void notNull() throws Exception {
        Objects.notNull(null);
    }
}
