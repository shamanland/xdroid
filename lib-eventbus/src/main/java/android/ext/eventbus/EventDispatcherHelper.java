package android.ext.eventbus;

import android.os.Bundle;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class EventDispatcherHelper {
    public static void init(EventDispatcherOwner owner) {
        if (owner.getEventDispatcherXmlId() != 0) {
            EventDispatcher dispatcher = EventDispatcherInflater.getInstance().inflate(owner.getContext(), owner.getEventDispatcherXmlId());
            owner.putCustomService(EventDispatcher.class.getName(), dispatcher);

            Bundle event = owner.extractInitialEvent();
            if (event != null) {
                EventBus.send(owner.getContext(), event);
            }
        }
    }

    private EventDispatcherHelper() {
        // disallow public access
    }
}
