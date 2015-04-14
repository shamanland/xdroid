package xdroid.options;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import xdroid.core.SharedPreferencesUtils;

import static xdroid.core.Global.getContext;
import static xdroid.core.ObjectUtils.cast;
import static xdroid.core.ObjectUtils.notNull;

public class OptionAccessor {
    private final Object mDefaultValue;
    private final Getter mGetter;
    private final Setter mSetter;
    private Object mValue;
    private Option mOption;

    public void setEnumConst(Enum<?> enumConst) {
        mOption = new EnumOption(enumConst);
    }

    public void setOption(Option option) {
        mOption = notNull(option);
    }

    public Getter getter() {
        return mGetter;
    }

    public Setter setter() {
        return mSetter;
    }

    private SharedPreferences getPrefs() {
        return getContext().getSharedPreferences(mOption.getClass().getName(), Context.MODE_PRIVATE);
    }

    public OptionAccessor(int defaultValue) {
        mDefaultValue = defaultValue;
        mGetter = new IntGetter();
        mSetter = new IntSetter();
    }

    public OptionAccessor(long defaultValue) {
        mDefaultValue = defaultValue;
        mGetter = new LongGetter();
        mSetter = new LongSetter();
    }

    public OptionAccessor(float defaultValue) {
        mDefaultValue = defaultValue;
        mGetter = new FloatGetter();
        mSetter = new FloatSetter();
    }

    public OptionAccessor(boolean defaultValue) {
        mDefaultValue = defaultValue;
        mGetter = new BooleanGetter();
        mSetter = new BooleanSetter();
    }

    public OptionAccessor(String defaultValue) {
        mDefaultValue = defaultValue;
        mGetter = new StringGetter();
        mSetter = new StringSetter();
    }

    public OptionAccessor(Set<String> defaultValue) {
        mDefaultValue = defaultValue;
        mGetter = new StringSetGetter();
        mSetter = new StringSetSetter();
    }

    public OptionAccessor(String firstValue, String... otherValues) {
        this(asSet(firstValue, otherValues));
    }

    private static Set<String> asSet(String firstValue, String... otherValues) {
        HashSet<String> result = new HashSet<>();
        result.add(firstValue);
        Collections.addAll(result, otherValues);
        return result;
    }

    public class Getter {
        public int asInt() {
            throw new UnsupportedOperationException();
        }

        public long asLong() {
            throw new UnsupportedOperationException();
        }

        public float asFloat() {
            throw new UnsupportedOperationException();
        }

        public boolean asBoolean() {
            throw new UnsupportedOperationException();
        }

        public String asString() {
            throw new UnsupportedOperationException();
        }

        public Set<String> asStringSet() {
            throw new UnsupportedOperationException();
        }
    }

    public class Setter {
        public boolean asInt(int value) {
            throw new UnsupportedOperationException();
        }

        public boolean asLong(long value) {
            throw new UnsupportedOperationException();
        }

        public boolean asFloat(float value) {
            throw new UnsupportedOperationException();
        }

        public boolean asBoolean(boolean value) {
            throw new UnsupportedOperationException();
        }

        public boolean asString(String value) {
            throw new UnsupportedOperationException();
        }

        public boolean asStringSet(Set<String> value) {
            throw new UnsupportedOperationException();
        }
    }

    protected class IntGetter extends Getter {
        public int asInt() {
            if (mValue == null) {
                mValue = SharedPreferencesUtils.getInt(getPrefs(), mOption.getName(), (Integer) mDefaultValue);
            }

            return (Integer) mValue;
        }
    }

    protected class IntSetter extends Setter {
        public boolean asInt(int value) {
            boolean changed = isChanged(mValue, value);
            mValue = value;
            getPrefs().edit().putInt(mOption.getName(), value).apply();
            return changed;
        }
    }

    protected class LongGetter extends Getter {
        public long asLong() {
            if (mValue == null) {
                mValue = SharedPreferencesUtils.getLong(getPrefs(), mOption.getName(), (Long) mDefaultValue);
            }

            return (Long) mValue;
        }
    }

    protected class LongSetter extends Setter {
        public boolean asLong(long value) {
            boolean changed = isChanged(mValue, value);
            mValue = value;
            getPrefs().edit().putLong(mOption.getName(), value).apply();
            return changed;
        }
    }

    protected class FloatGetter extends Getter {
        public float asFloat() {
            if (mValue == null) {
                mValue = SharedPreferencesUtils.getFloat(getPrefs(), mOption.getName(), (Float) mDefaultValue);
            }

            return (Float) mValue;
        }
    }

    protected class FloatSetter extends Setter {
        public boolean asFloat(float value) {
            boolean changed = isChanged(mValue, value);
            mValue = value;
            getPrefs().edit().putFloat(mOption.getName(), value).apply();
            return changed;
        }
    }

    protected class BooleanGetter extends Getter {
        public boolean asBoolean() {
            if (mValue == null) {
                mValue = SharedPreferencesUtils.getBoolean(getPrefs(), mOption.getName(), (Boolean) mDefaultValue);
            }

            return (Boolean) mValue;
        }
    }

    protected class BooleanSetter extends Setter {
        public boolean asBoolean(boolean value) {
            boolean changed = isChanged(mValue, value);
            mValue = value;
            getPrefs().edit().putBoolean(mOption.getName(), value).apply();
            return changed;
        }
    }

    protected class StringGetter extends Getter {
        public String asString() {
            if (mValue == null) {
                mValue = SharedPreferencesUtils.getString(getPrefs(), mOption.getName(), (String) mDefaultValue);
            }

            return (String) mValue;
        }
    }

    protected class StringSetter extends Setter {
        public boolean asString(String value) {
            boolean changed = isChanged(mValue, value);
            mValue = value;
            getPrefs().edit().putString(mOption.getName(), value).apply();
            return changed;
        }
    }

    protected class StringSetGetter extends Getter {
        public Set<String> asStringSet() {
            if (mValue == null) {
                Set<String> defaultValue = cast(mDefaultValue);
                mValue = SharedPreferencesUtils.getStringSet(getPrefs(), mOption.getName(), defaultValue);
            }

            return cast(mValue);
        }
    }

    protected class StringSetSetter extends Setter {
        public boolean asStringSet(Set<String> value) {
            boolean changed = isChanged(mValue, value);
            mValue = value;
            getPrefs().edit().putStringSet(mOption.getName(), value).apply();
            return changed;
        }
    }

    protected static boolean isChanged(Object a, Object b) {
        return (a == null) ? (b != null) : !a.equals(b);
    }
}
