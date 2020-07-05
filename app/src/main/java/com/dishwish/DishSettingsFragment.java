package com.dishwish;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

public class DishSettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);

        ListPreference listOrderPreference = findPreference(getString(R.string.list_order_key));
        listOrderPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                DishFragment.LIST_ORDER_CHANGE = true;
                return true;
            }
        });

        SwitchPreferenceCompat nightModePreference = findPreference(getString(R.string.night_mode_key));

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            nightModePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if ((Boolean) newValue) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                    }

                    return true;
                }
            });
        } else {
            nightModePreference.setVisible(false);
        }

        Preference ratePreference = findPreference(getString(R.string.rate_key));
        ratePreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.dishwish"));
                startActivity(intent);
                return false;
            }
        });
    }
}
