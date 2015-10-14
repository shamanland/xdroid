package xdroid.example.settings;

import xdroid.options.OptionAccessor;

public enum Settings {
    MY_FLAG(new OptionAccessor(true)),
    MY_NUMBER(new OptionAccessor(42));

    public final OptionAccessor.Getter get;
    public final OptionAccessor.Setter set;

    private Settings(OptionAccessor accessor) {
        accessor.setEnumConst(this);
        get = accessor.getter();
        set = accessor.setter();
    }
}
