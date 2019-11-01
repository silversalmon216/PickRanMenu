package com.factory.salmon.pickranmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    static final private String SETTINGKEY_SERVER="isOnServer";
    static final private String SETTINGKEY_GPS="isOnGPS";

    private DrawerLayout drawer;
    private NavigationView navigation;

    private SharedPreferences preferences;
    private Toolbar toolbar;

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer=findViewById(R.id.Main_drawer);
        navigation=findViewById(R.id.Main_navigation);
        toolbar=findViewById(R.id.Main_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toggle=new ActionBarDrawerToggle(this,drawer,R.string.app_name,R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();
        drawer.addDrawerListener(toggle);

        preferences=getSharedPreferences("Setting",MODE_PRIVATE);
        if(preferences.getBoolean(SETTINGKEY_SERVER,false)) SettingChange(R.id.MainNavigation_setting_isUseServer);
        if(preferences.getBoolean(SETTINGKEY_GPS,false))    SettingChange(R.id.MainNavigation_setting_isUseGPS);
        navigation.setNavigationItemSelectedListener(navigationListener);

    }

    public void SettingChange(int CompoundID){
        MenuItem item=navigation.getMenu().findItem(CompoundID);
        CompoundButton check=(CompoundButton) MenuItemCompat.getActionView(item);
        check.setChecked(!check.isChecked());
        if(CompoundID==R.id.MainNavigation_setting_isUseServer) {
            G.isUseServer = check.isChecked();
            preferences.edit().putBoolean(SETTINGKEY_SERVER,check.isChecked()).commit();
        }
        else if(CompoundID==R.id.MainNavigation_setting_isUseGPS){
            G.isUseGPS=check.isChecked();
            preferences.edit().putBoolean(SETTINGKEY_GPS,check.isChecked()).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        toggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    NavigationView.OnNavigationItemSelectedListener navigationListener=new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch(item.getItemId()){
                case R.id.MainNavigation_setting_menuNumber:

                    break;

                case R.id.MainNavigation_setting_isUseServer:
                case R.id.MainNavigation_setting_isUseGPS:
                    SettingChange(item.getItemId());
                    break;
            }
            return false;
        }
    };



}

