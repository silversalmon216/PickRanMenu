package com.factory.salmon.pickranmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private final static int DATABASE_VERSION=1;
    private final static String PREFERENCES_NAME="PreferencesPickRanMenuAPP";
    private final static String PREFERENCES_KEY_ISDB="IsDatabaseCreated";

    public final static String PREFERENCES_KEY_NUM_RECENT="MyNumRecent";
    public final static String PREFERENCES_KEY_NUM_RECENT_MAX="MyNumMaxRecent";
    public final static String PREFERENCES_KEY_NUM_RECENT_SELECT="MyNumSelectRecent";
    public final static String PREFERENCES_KEY_NUM_FAVORITE="MyNumFavorite";
    public final static String PREFERENCES_KEY_NUM_FAVORITE_MAX="MyNumMaxFavorite";
    public final static String PREFERENCES_KEY_NUM_FAVORITE_SELECT="MyNumSelectFavorite";
    public final static String PREFERENCES_KEY_NUM_LIST="MyNumList";
    public final static String PREFERENCES_KEY_NUM_LIST_MAX="MyNumMaxList";
    public final static String PREFERENCES_KEY_NUM_LIST_SELECT="MyNumSelectList";
    public final static String PREFERENCES_KEY_NUM_SERVER="ServerNumFavorite";
    public final static String PREFERENCES_KEY_NUM_SERVER_MAX="ServerNumMaxFavorite";
    public final static String PREFERENCES_KEY_NUM_SERVER_SELECT="ServerNumSelectFavorite";

    public DrawerLayout drawer;
    public NavigationView navigation;
    private BottomNavigationView bottomNavigation;

    private SharedPreferences preferences_fragment;
    private SharedPreferences preferences_database;
    private Toolbar toolbar;

    private ActionBarDrawerToggle toggle;
    public MenuDataBase menuDataBase;

    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        G.main=this;

        drawer=findViewById(R.id.Main_drawer);
        navigation=findViewById(R.id.Main_navigation);
        toolbar=findViewById(R.id.Main_toolbar);
        bottomNavigation=findViewById(R.id.Main_bottomNavigation);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toggle=new ActionBarDrawerToggle(this,drawer,R.string.app_name,R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();
        drawer.addDrawerListener(toggle);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationListener());

        preferences_fragment= PreferenceManager.getDefaultSharedPreferences(this);

        G.isUseServer=preferences_fragment.getBoolean("isOnServer",false);
        G.isUseGPS=preferences_fragment.getBoolean("isOnGPS",false);

        preferences_database=getSharedPreferences(PREFERENCES_NAME,MODE_PRIVATE);
        menuDataBase=new MenuDataBase(this);
        if(preferences_database.getInt(PREFERENCES_KEY_ISDB,0)!=DATABASE_VERSION){
            menuDataBase.InsertMenu();
            SharedPreferences.Editor edit=preferences_database.edit();
            edit.putInt(PREFERENCES_KEY_ISDB,DATABASE_VERSION);

            String[] favors=getResources().getStringArray(R.array.favor);
            for(String favor : favors)
                edit.putBoolean(favor,true);

            edit.putInt(PREFERENCES_KEY_NUM_RECENT,5);
            edit.putInt(PREFERENCES_KEY_NUM_RECENT_MAX,10);
            edit.putInt(PREFERENCES_KEY_NUM_RECENT_SELECT,10);
            edit.putInt(PREFERENCES_KEY_NUM_FAVORITE,5);
            edit.putInt(PREFERENCES_KEY_NUM_FAVORITE_MAX,10);
            edit.putInt(PREFERENCES_KEY_NUM_FAVORITE_SELECT,10);
            edit.putInt(PREFERENCES_KEY_NUM_LIST, 5);
            edit.putInt(PREFERENCES_KEY_NUM_LIST_MAX, 10);
            edit.putInt(PREFERENCES_KEY_NUM_LIST_SELECT,10);
            edit.putInt(PREFERENCES_KEY_NUM_SERVER,0);
            edit.putInt(PREFERENCES_KEY_NUM_SERVER_MAX,0);
            edit.putInt(PREFERENCES_KEY_NUM_SERVER_SELECT,0);

            edit.commit();
        }

        G.menuNumMax[0]=preferences_database.getInt(PREFERENCES_KEY_NUM_RECENT_MAX,0);
        G.menuNumMax[1]=preferences_database.getInt(PREFERENCES_KEY_NUM_FAVORITE_MAX,0);
        G.menuNumMax[2]=preferences_database.getInt(PREFERENCES_KEY_NUM_LIST_MAX,0);
        G.menuNumMax[3]=!G.isUseServer ? 0 : preferences_database.getInt(PREFERENCES_KEY_NUM_SERVER_MAX,0);

        G.menuNum[0]=preferences_database.getInt(PREFERENCES_KEY_NUM_RECENT,0);
        G.menuNum[1]=preferences_database.getInt(PREFERENCES_KEY_NUM_FAVORITE,0);
        G.menuNum[2]=preferences_database.getInt(PREFERENCES_KEY_NUM_LIST,0);
        G.menuNum[3]=preferences_database.getInt(PREFERENCES_KEY_NUM_SERVER,0);

        G.menuNumSelect[0]=preferences_database.getInt(PREFERENCES_KEY_NUM_RECENT_SELECT,0);
        G.menuNumSelect[1]=preferences_database.getInt(PREFERENCES_KEY_NUM_FAVORITE_SELECT,0);
        G.menuNumSelect[2]=preferences_database.getInt(PREFERENCES_KEY_NUM_LIST_SELECT,0);
        G.menuNumSelect[3]=preferences_database.getInt(PREFERENCES_KEY_NUM_SERVER_SELECT,0);

        menuDataBase.GetMaxNum();

        String[] favor=getResources().getStringArray(R.array.favor);
        G.favorOnOff=new Boolean[favor.length];
        for(int i=0;i<favor.length;i++){
            G.favorOnOff[i]=preferences_database.getBoolean(favor[i],true);
        }

        FragmentChange(1,null);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        toggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    public void SwitchFavor(String favor,boolean isMakeOn){
        menuDataBase.SwitchFavor(favor,isMakeOn);
        preferences_database.edit().putBoolean(favor,isMakeOn).commit();
    }

    public void SetMenuNum(String key,int num){
        preferences_database.edit().putInt(key,num).commit();
    }

    public void FragmentChange(int index, Bundle bundle){

        switch(index){

            case 1:
                currentFragment=new MainFragment();
                break;

            case 2:
                currentFragment=new MenuListFragment();
                break;

            case 3:
                currentFragment=new MenuPickFragment(bundle);
                break;

            case 4:
                currentFragment=new MenuCheckFragment(bundle);
                break;

            case 5:
                currentFragment=new MenuSelectFragment(bundle);
                break;

            case 6:
                break;

        }

        getSupportFragmentManager().beginTransaction().replace(R.id.Main_frame,currentFragment).commit();

    }

    private class BottomNavigationListener implements BottomNavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch(item.getItemId()){
                case R.id.Main_Bottom_menu_picture:
                    FragmentChange(6,null);
                    new AlertDialog.Builder(MainActivity.this).setMessage("준비중입니다.").setPositiveButton("OK",null).create().show();
                    break;

                case R.id.Main_Bottom_menu_restaurant:

                    new MenuPickAlertDialog();
                    break;

                case R.id.Main_Bottom_menu_search:
                    new AlertDialog.Builder(MainActivity.this).setMessage("준비중입니다.").setPositiveButton("OK",null).create().show();
                    break;

            }

            return true;
        }
    }

}

