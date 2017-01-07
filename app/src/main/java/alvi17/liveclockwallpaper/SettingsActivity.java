package alvi17.liveclockwallpaper;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

/**
 * Created by User on 12/31/2016.
 */

public class SettingsActivity extends PreferenceActivity{

    /** Key for display hand sec. */
    public static final String DISPLAY_HAND_SEC_KEY = "displayHandSec";
    private Preference displayHandSecPref;
    CheckBoxPreference center,topleft,topright,bottomleft,bottomright,width50,width65,width40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
        displayHandSecPref = findPreference(DISPLAY_HAND_SEC_KEY);
        center=(CheckBoxPreference) findPreference("center");
        center.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                boolean value=(Boolean)o;
                if(value)
                {
                    topleft.setChecked(false);
                    topright.setChecked(false);
                    bottomright.setChecked(false);
                    bottomleft.setChecked(false);

                    return true;
                }


                return false;
            }
        });
        topleft=(CheckBoxPreference)findPreference("topleft");
        topleft.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                boolean value=(Boolean)o;
                if(value)
                {
                    center.setChecked(false);
                    topright.setChecked(false);
                    bottomright.setChecked(false);
                    bottomleft.setChecked(false);
                    return true;
                }
                return false;
            }
        });

        topright=(CheckBoxPreference)findPreference("topright");
        topright.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                boolean value=(Boolean)o;
                if(value)
                {
                    topleft.setChecked(false);
                    center.setChecked(false);
                    bottomright.setChecked(false);
                    bottomleft.setChecked(false);
                    return true;
                }
                return false;
            }
        });
        bottomleft=(CheckBoxPreference)findPreference("bottomleft");
        bottomleft.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                boolean value=(Boolean)o;
                if(value)
                {
                    topleft.setChecked(false);
                    topright.setChecked(false);
                    bottomright.setChecked(false);
                    center.setChecked(false);
                    return true;
                }
                return false;
            }
        });
        bottomright=(CheckBoxPreference)findPreference("bottomright");
        bottomright.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                boolean value=(Boolean)o;
                if(value)
                {
                    topleft.setChecked(false);
                    topright.setChecked(false);
                    center.setChecked(false);
                    bottomleft.setChecked(false);
                    return true;
                }
                return false;
            }
        });
        width40=(CheckBoxPreference)findPreference("clockwidth40");
        width40.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                boolean value=(Boolean)o;
                if(value)
                {
                    width50.setChecked(false);
                    width65.setChecked(false);
                    return true;
                }
                return false;
            }
        });
        width50=(CheckBoxPreference)findPreference("clockwidth50");
        width50.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                boolean value=(Boolean)o;
                if(value)
                {
                    width40.setChecked(false);
                    width65.setChecked(false);
                    return true;
                }
                return false;
            }
        });
        width65=(CheckBoxPreference)findPreference("clockwidth65");
        width65.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                boolean value=(Boolean)o;
                if(value)
                {
                    width50.setChecked(false);
                    width40.setChecked(false);
                    return true;
                }
                return false;
            }
        });
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
                        Toast.LENGTH_SHORT);
                return true;
            }

            return false;
        }
    };


}

