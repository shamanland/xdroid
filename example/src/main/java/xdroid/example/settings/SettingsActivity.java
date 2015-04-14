package xdroid.example.settings;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import xdroid.eventbus.EventBusActivity;
import xdroid.example.R;

public class SettingsActivity extends EventBusActivity {
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        ViewGroup container = new FrameLayout(getContext());
        container.setId(R.id.container);
        setContentView(container);

        if (state == null) {
            getFm().beginTransaction().add(R.id.container, new SettingsFragment()).commit();
        }
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.settings);

            CheckBoxPreference myFlag = (CheckBoxPreference) findPreference("my.flag");
            myFlag.setChecked(Settings.MY_FLAG.get.asBoolean());
            myFlag.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (newValue instanceof Boolean) {
                        Settings.MY_FLAG.set.asBoolean((Boolean) newValue);
                        return true;
                    }

                    return false;
                }
            });

            EditTextPreference myNumber = (EditTextPreference) findPreference("my.number");
            myNumber.setText(String.valueOf(Settings.MY_NUMBER.get.asInt()));
            myNumber.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (newValue instanceof String) {
                        Settings.MY_NUMBER.set.asInt(Integer.parseInt((String) newValue));
                        return true;
                    }

                    return false;
                }
            });
        }
    }
}
