package com.factory.salmon.pickranmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;


public class MenuSelectFragment extends Fragment {

    String menuName;
    int menuPictureUri;

    public MenuSelectFragment(Bundle bundle){
        menuPictureUri=bundle.getInt("MenuPictureUri");
        menuName=bundle.getString("MenuName");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_menu_select,container,false);
        Glide.with(G.main).load(menuPictureUri).into((ImageView)v.findViewById(R.id.MenuSelect_img));
        ((BoldTextView)v.findViewById(R.id.MenuSelect_txt)).setText(menuName);

        return v;
    }
}
