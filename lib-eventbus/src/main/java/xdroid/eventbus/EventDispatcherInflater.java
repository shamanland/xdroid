package xdroid.eventbus;

import android.content.Context;
import android.content.res.TypedArray;
import xdroid.eventbus.R;
import xdroid.inflater.AbstractInflater;
import xdroid.inflater.Inflatable;
import android.util.AttributeSet;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class EventDispatcherInflater extends AbstractInflater<EventDispatcher, EventTransmitter> {
    private static final Map<String, Factory> sFactories;
    private static final EventDispatcherInflater sInstance;

    static {
        sFactories = new HashMap<String, Factory>();
        sFactories.put(EventTransmitter.class.getSimpleName(), new TransmitterFactory());
        sFactories.put(EventDelivery.class.getSimpleName(), new DeliveryFactory());
        sFactories.put(EventForwarder.class.getSimpleName(), new ForwarderFactory());
        sFactories.put(EventFinish.class.getSimpleName(), new FinishFactory());

        sInstance = new EventDispatcherInflater();
    }

    public static EventDispatcherInflater getInstance() {
        return sInstance;
    }

    public EventDispatcherInflater() {
        super(EventTransmitter.class);
    }

    @Override
    protected EventDispatcher createFromTag(Context context, XmlPullParser parser, EventTransmitter parent, AttributeSet attrs) throws XmlPullParserException {
        Factory factory = sFactories.get(parser.getName());
        if (factory == null) {
            throw new XmlPullParserException(parser.getName());
        }

        EventDispatcher result = factory.create(context);
        int eventId = 0;

        if (parent != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EventDispatcher);
            if (a != null) {
                try {
                    eventId = a.getResourceId(R.styleable.EventDispatcher_id, eventId);
                } finally {
                    a.recycle();
                }
            }
        }

        if (result instanceof Inflatable) {
            ((Inflatable) result).inflate(context, parser, attrs);
        }

        if (eventId != 0) {
            parent.put(eventId, result);
        }

        return result;
    }

    public static String readClass(Context context, TypedArray a, int index) {
        String result = a.getString(index);
        if (result == null) {
            throw new IllegalArgumentException();
        }

        if (result.length() == 0) {
            throw new IllegalArgumentException();
        }

        if (result.charAt(0) == '.') {
            result = context.getPackageName().concat(result);
        }

        return result;
    }

    static abstract class Factory {
        abstract EventDispatcher create(Context context);
    }

    static class TransmitterFactory extends Factory {
        @Override
        EventDispatcher create(Context context) {
            return new EventTransmitter();
        }
    }

    static class DeliveryFactory extends Factory {
        @Override
        EventDispatcher create(Context context) {
            return new EventDelivery(context);
        }
    }

    static class ForwarderFactory extends Factory {
        @Override
        EventDispatcher create(Context context) {
            return new EventForwarder(context);
        }
    }

    static class FinishFactory extends Factory {
        @Override
        EventDispatcher create(Context context) {
            return new EventFinish(context);
        }
    }
}
