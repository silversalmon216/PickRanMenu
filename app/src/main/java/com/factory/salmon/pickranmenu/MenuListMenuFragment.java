package com.factory.salmon.pickranmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;

public class MenuListMenuFragment extends Fragment {

    RecyclerView recyclerView;

    ArrayList<String> menuList;
    ArrayList<Integer> isOnList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_menu_list_recycler,container,false);
        recyclerView=v.findViewById(R.id.MenuList_recycler);
        recyclerView.addItemDecoration(new DividerItemDecoration(G.main,DividerItemDecoration.VERTICAL));

//        menuList=G.main.menuDataBase.GetArrayList("menuName");
        menuList=new ArrayList<>();
        for(String s : getActivity().getResources().getStringArray(R.array.menu)){
            menuList.add(s);
        }
        isOnList=G.main.menuDataBase.GetArrayList("OnOff");

        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v=getLayoutInflater().inflate(R.layout.item_menu_list_switch,parent,false);
                VH holder=new VH(v);

                return holder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                VH viewHolder=(VH)holder;
                viewHolder.txt_menuName.setText(menuList.get(position));
                viewHolder.switch_menu.setOnCheckedChangeListener(null);

                if(isOnList.get(position)==0)
                    viewHolder.switch_menu.setChecked(false);
                else
                    viewHolder.switch_menu.setChecked(true);

                SwitchListener listener=new SwitchListener(menuList.get(position),position);
                viewHolder.switch_menu.setOnCheckedChangeListener(listener);

            }

            @Override
            public int getItemCount() {
                return menuList.size();
            }

            class VH extends RecyclerView.ViewHolder{

                RegularTextView txt_menuName;
                SwitchMaterial switch_menu;

                public VH(@NonNull View itemView) {
                    super(itemView);

                    txt_menuName=itemView.findViewById(R.id.MenuList_Switch_Item_txt);
                    switch_menu=itemView.findViewById(R.id.MenuList_Switch_Item_switch);

                }


            }

        });

        return v;
    }

    private class SwitchListener implements CompoundButton.OnCheckedChangeListener{

        String menu;
        int index;

        public SwitchListener(String menu, int index){
            this.menu=menu;
            this.index=index;
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            G.main.menuDataBase.SwitchMenu(menu,b);
            int isOn= b ? 1 : 0;
            isOnList.set(index,isOn);
//            new AlertDialog.Builder(G.main).setMessage(index+" - "+isOnList.get(index)+" - "+menu+" - "+b).setPositiveButton("OK",null).create().show();
        }
    }

}
