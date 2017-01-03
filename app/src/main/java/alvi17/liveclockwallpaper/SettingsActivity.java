package alvi17.liveclockwallpaper;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

/**
 * Created by User on 12/31/2016.
 */

public class SettingsActivity extends PreferenceActivity {

    /** Key for display hand sec. */
    public static final String DISPLAY_HAND_SEC_KEY = "displayHandSec";
    private Preference displayHandSecPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
        displayHandSecPref = findPreference(DISPLAY_HAND_SEC_KEY);
        displayHandSecPref.setOnPreferenceChangeListener(prefChangeListener);
    }

    private Preference.OnPreferenceChangeListener prefChangeListener = new Preference.OnPreferenceChangeListener() {

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {

            if (DISPLAY_HAND_SEC_KEY.equals(preference.getKey())) {
                boolean value = (Boolean) newValue;
                Toast.makeText(getApplicationContext(),
                        R.string.display_hand_sec_txt + " "
                                + (value ? R.string.enabled : R.string.disabled),
                        Toast.LENGTH_SHORT).show();
                return true;
            }

            return false;
        }
    };
}

