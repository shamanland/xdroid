package xdroid.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertSame;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class ObjectsTest {
    @Test(expected = NullPointerException.class)
    public void notNullWithNull() throws Exception {
        Objects.notNull(null);
    }

    @Test
    public void notNullWithObject() throws Exception {
        Object obj = new Object();
        assertSame(obj, Objects.notNull(obj));
    }
}
