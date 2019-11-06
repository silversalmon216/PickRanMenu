package com.factory.salmon.pickranmenu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import androidx.annotation.Nullable;

public class MainNavigationMenu extends PreferenceFragment {

    SharedPreferences preferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.menu_main_navigation);

        preferences= PreferenceManager.getDefaultSharedPreferences(getActivity());

    }

    @Override
    public void onResume() {
        super.onResume();
        preferences.registerOnSharedPreferenceChangeListener(preferencesListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        preferences.unregisterOnSharedPreferenceChangeListener(preferencesListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener preferencesListener=new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            if(s.equals("menu")) {
//                G.main.drawer.closeDrawer(G.main.navigation);
//                G.main.FragmentChange(1);
            }
        }
    };

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {

        if(preference.getKey().equals("menu")){
            G.main.drawer.closeDrawer(G.main.navigation);
            G.main.FragmentChange(1);
        }

        return false;
    }
}
