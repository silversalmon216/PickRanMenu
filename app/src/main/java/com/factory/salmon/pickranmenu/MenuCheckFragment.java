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

import java.util.ArrayList;
import java.util.Collections;

public class MenuCheckFragment extends Fragment {

    Bundle bundle;
    String menuName;
    int menuUri;

    public MenuCheckFragment(Bundle bundle){
        this.bundle=bundle;
        ArrayList<String> menuNameList=bundle.getStringArrayList("menuNameList");
        ArrayList<Integer> menuUriList=bundle.getIntegerArrayList("menuUriList");
        ArrayList<String> newMenuNameList=new ArrayList<>();
        for(String s : menuNameList)
            newMenuNameList.add(s);
        Collections.shuffle(newMenuNameList);
        String selectMenu=newMenuNameList.get(0);
        int index=menuNameList.indexOf(selectMenu);

        menuName=menuNameList.get(index);
        menuUri=menuUriList.get(index);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_menu_check,container,false);
        Glide.with(G.main).load(menuUri).into((ImageView)v.findViewById(R.id.MenuCheck_img));
        ((BoldTextView)v.findViewById(R.id.MenuCheck_txt)).setText(menuName);

        v.findViewById(R.id.MenuCheck_btnSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("MenuName",menuName);
                bundle.putInt("MenuPictureUri",menuUri);
                G.main.FragmentChange(5,bundle);
            }
        });

        v.findViewById(R.id.MenuCheck_btnRandom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                G.main.FragmentChange(4,bundle);
            }
        });

        return v;
    }
}
