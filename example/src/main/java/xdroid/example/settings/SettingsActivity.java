package xdroid.example.settings;

import android.app.Activity;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import xdroid.example.R;

public class SettingsActivity extends Activity {
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        ViewGroup container = new FrameLayout(this);
        container.setId(R.id.container);
        setContentView(container);
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
