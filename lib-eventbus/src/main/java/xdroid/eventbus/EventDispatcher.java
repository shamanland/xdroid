package xdroid.eventbus;

import android.os.Bundle;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface EventDispatcher {
    boolean onNewEvent(int eventId, Bundle event);
}
