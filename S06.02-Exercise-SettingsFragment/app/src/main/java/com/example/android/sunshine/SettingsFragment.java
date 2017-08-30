package com.example.android.sunshine;

import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceScreen;

import java.util.List;

/**
 * Created by jimschumacher on 27/08/2017.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Preference preference = findPreference(key);
        if (null != preference) {
            if (!(preference instanceof CheckBoxPreference)) {
                setPreferenceSummary(preference, sharedPreferences.getString(key, ""));
            }
        }

    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference_screen);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen = getPreferenceScreen();
        int count = prefScreen.getPreferenceCount();

        for (int i = 0; i < count ; i++) {
            Preference currentPref = prefScreen.getPreference(i);
            if (!(currentPref instanceof CheckBoxPreference))  {
                String value = sharedPreferences.getString(prefScreen.getKey(), "");
                setPreferenceSummary(currentPref,value);
            }
        }

    }

    private void setPreferenceSummary(Preference p, Object value) {

        String stringValue = value.toString();
        String key = p.getKey();

        if (p instanceof ListPreference) {
            ListPreference listPref = (ListPreference) p;
            int prefIndex = listPref.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                p.setSummary(listPref.getEntries()[prefIndex]);
            }
        } else {
            p.setSummary(stringValue);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
               /* Register the preference change listener */
                        getPreferenceScreen().getSharedPreferences()
                                .registerOnSharedPreferenceChangeListener(this);

    }
    @Override
    public void onStop() {
        super.onStop();
               /* Register the preference change listener */
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);

    }

    // DONE (10) Implement OnSharedPreferenceChangeListener from SettingsFragment

    // DONE (8) Create a method called setPreferenceSummary that accepts a Preference and an Object and sets the summary of the preference

    // DONE (5) Override onCreatePreferences and add the preference xml file using addPreferencesFromResource

    // Do step 9 within onCreatePreference
    // DONE (9) Set the preference summary on each preference that isn't a CheckBoxPreference

    // DONE (13) Unregister SettingsFragment (this) as a SharedPreferenceChangedListener in onStop

    // DONE (12) Register SettingsFragment (this) as a SharedPreferenceChangedListener in onStart

    // DONE (11) Override onSharedPreferenceChanged to update non CheckBoxPreferences when they are changed
}
