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

public class MenuListFavorFragment extends Fragment {

    RecyclerView recyclerView;

    ArrayList<String> favor=new ArrayList<>();
    Boolean[] favorOnOff;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_menu_list_recycler, container,false);
        recyclerView=v.findViewById(R.id.MenuList_recycler);
        recyclerView.addItemDecoration(new DividerItemDecoration(G.main,DividerItemDecoration.VERTICAL));

        favorOnOff=G.favorOnOff;
        for(String s : getResources().getStringArray(R.array.favor))
            favor.add(s);

        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v=getLayoutInflater().inflate(R.layout.item_menu_list_switch,parent,false);
                ViewHolder vh=new ViewHolder(v);

                return vh;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ViewHolder vh=(ViewHolder)holder;
                vh.switch_favor.setOnCheckedChangeListener(null);

                vh.txt_favor.setText(favor.get(position));
                vh.switch_favor.setChecked(true);
                if(!favorOnOff[position])   vh.switch_favor.setChecked(false);

                vh.switch_favor.setOnCheckedChangeListener(new SwitchListener(favor.get(position),position));

            }

            @Override
            public int getItemCount() {
                return favor.size();
            }

            class ViewHolder extends RecyclerView.ViewHolder{

                RegularTextView txt_favor;
                SwitchMaterial switch_favor;

                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    txt_favor=itemView.findViewById(R.id.MenuList_Switch_Item_txt);
                    switch_favor=itemView.findViewById(R.id.MenuList_Switch_Item_switch);
                }
            }

        });

        return v;
    }

    private class SwitchListener implements CompoundButton.OnCheckedChangeListener{

        String favor;
        int index;

        public SwitchListener(String favor, int index) { this.favor = favor; this.index = index; }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            G.main.SwitchFavor(favor,b);
            G.favorOnOff[index]=b;
        }
    }

}
