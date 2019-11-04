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

    public DrawerLayout drawer;
    public NavigationView navigation;

    private SharedPreferences preferences;
    private Toolbar toolbar;

    private ActionBarDrawerToggle toggle;

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

        preferences=getSharedPreferences("Setting",MODE_PRIVATE);

        G.isUseServer=preferences.getBoolean("isOnServer",false);
        G.isUseGPS=preferences.getBoolean("isOnGPS",false);

        Toast.makeText(this, "11"+getIntent().getStringExtra("a"), Toast.LENGTH_SHORT).show();

    }

    public void ClickA(){
        Toast.makeText(this, "asdfqwer", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        toggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

}

