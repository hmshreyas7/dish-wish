package com.dishwish;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

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
