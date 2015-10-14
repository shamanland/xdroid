package xdroid.options;

import static xdroid.core.ObjectUtils.notEmpty;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class SimpleOption implements Option {
    private final String mName;
    public final OptionAccessor.Getter get;
    public final OptionAccessor.Setter set;

    @Override
    public String getName() {
        return mName;
    }

    public SimpleOption(String name, OptionAccessor accessor) {
        mName = notEmpty(name);
        accessor.setOption(this);
        get = accessor.getter();
        set = accessor.setter();
    }
}
