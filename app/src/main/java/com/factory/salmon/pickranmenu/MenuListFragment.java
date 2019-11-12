package com.factory.salmon.pickranmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class MenuListFragment extends Fragment {

    TabLayout tab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        View frame=inflater.inflate(R.layout.fragment_menu_list_container,container,false);
        tab=frame.findViewById(R.id.MenuList_tab);

        getFragmentManager().beginTransaction().replace(R.id.MenuList_frame,new MenuListMenuFragment()).commit();

        tab.addTab(tab.newTab().setText("메뉴목록"));
        tab.addTab(tab.newTab().setText("메뉴갯수"));
        tab.addTab(tab.newTab().setText("메뉴취향"));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Fragment nextFragment=null;

                switch(tab.getPosition()){
                    case 0:
                        nextFragment=new MenuListMenuFragment();
                        break;

                    case 1:
                        nextFragment=new MenuListNumFragment();
                        break;

                    case 2:
                        nextFragment=new MenuListFavorFragment();
                        break;

                }

                getFragmentManager().beginTransaction().replace(R.id.MenuList_frame,nextFragment).commit();

            }

            @Override public void onTabUnselected(TabLayout.Tab tab) { }
            @Override public void onTabReselected(TabLayout.Tab tab) { }
        });

        return frame;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_menu_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.Menu_menuList_done){
            G.main.FragmentChange(1);
        }

        return super.onOptionsItemSelected(item);
    }
}
