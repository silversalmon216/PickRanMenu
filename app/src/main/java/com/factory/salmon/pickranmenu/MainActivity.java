package com.factory.salmon.pickranmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private final static int DATABASE_VERSION=1;
    final static String PREFERENCES_NAME="PreferencesPickRanMenuAPP";
    final static String PREFERENCES_KEY_ISDB="IsDatabaseCreated";

    public DrawerLayout drawer;
    public NavigationView navigation;

    private SharedPreferences preferences_fragment;
    private SharedPreferences preferences_database;
    private Toolbar toolbar;

    private ActionBarDrawerToggle toggle;
    private MenuDataBase menuDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        G.main=this;

        drawer=findViewById(R.id.Main_drawer);
        navigation=findViewById(R.id.Main_navigation);
        toolbar=findViewById(R.id.Main_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toggle=new ActionBarDrawerToggle(this,drawer,R.string.app_name,R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();
        drawer.addDrawerListener(toggle);

        preferences_fragment= PreferenceManager.getDefaultSharedPreferences(this);

        G.isUseServer=preferences_fragment.getBoolean("isOnServer",false);
        G.isUseGPS=preferences_fragment.getBoolean("isOnGPS",false);

        preferences_database=getSharedPreferences(PREFERENCES_NAME,MODE_PRIVATE);
        menuDataBase=new MenuDataBase(this);
        if(preferences_database.getInt(PREFERENCES_KEY_ISDB,0)!=DATABASE_VERSION){
            menuDataBase.InsertMenu();
            preferences_database.edit().putInt(PREFERENCES_KEY_ISDB,DATABASE_VERSION).commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        toggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    public void FragmentChange(int index){
        Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
        if(index==1){
            Toast.makeText(this, "bbb", Toast.LENGTH_SHORT).show();
            menuDataBase.SwitchMenu("김밥");
        }
    }

}

