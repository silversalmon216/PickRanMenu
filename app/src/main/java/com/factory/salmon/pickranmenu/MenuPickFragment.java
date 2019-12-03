package com.factory.salmon.pickranmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.ArrayList;
import java.util.Collections;

public class MenuPickFragment extends Fragment {

    RecyclerView recycler;
    int[] menuNumMaxResult;
    int[] menuNumResult;
    ArrayList<MenuItem> menu=new ArrayList<>();
    ArrayList<MaterialCheckBox> checkBoxArray=new ArrayList<>();

    int pickMenu=-1;

    public MenuPickFragment(Bundle bundle){
        menuNumMaxResult=bundle.getIntArray("MenuNumMaxResult");
        menuNumResult=bundle.getIntArray("MenuNumResult");

        for(int i=0;i<3;i++) {
            ArrayList<MenuItem> list = G.main.menuDataBase.GetRankingList(i, menuNumMaxResult[i]);
            for(MenuItem m : list)
                for(int n=0;n<menu.size()-1;n++)
                    if(menu.get(n).name.equals(m.name))
                        menu.remove(n);

//                for(MenuItem n : menu)
//                    if(n.name.equals(m.name))
//                        menu.remove(n);

            menu.addAll(list.subList(0, menuNumResult[i]));
        }
        Collections.shuffle(menu);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_menu_pick,container,false);

        recycler=v.findViewById(R.id.MenuPick_recycler);
        recycler.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v=getLayoutInflater().inflate(R.layout.item_menu_pick_picture,parent,false);
                ViewHolder vh=new ViewHolder(v);

                return vh;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ViewHolder vh=(ViewHolder)holder;

                vh.menuName.setText(menu.get(position).name);
                Glide.with(G.main).load(menu.get(position).pictureUri).into(vh.menuImg);

                if(position==pickMenu) vh.checkBox.setChecked(true);
                else    vh.checkBox.setChecked(false);


            }

            @Override
            public int getItemCount() {
                return menu.size();
            }

            class ViewHolder extends RecyclerView.ViewHolder{

                ImageView menuImg;
                RegularTextView menuName;
                MaterialCheckBox checkBox;

                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    menuImg=itemView.findViewById(R.id.MenuPick_item_img);
                    menuName=itemView.findViewById(R.id.MenuPick_item_name);
                    checkBox=itemView.findViewById(R.id.MenuPick_item_check);
                    checkBoxArray.add(checkBox);

                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int index=getAdapterPosition();
                            if(pickMenu!=-1){
                                checkBoxArray.get(pickMenu).setChecked(false);
                                checkBoxArray.get(pickMenu).setVisibility(View.INVISIBLE);
                            }
                            pickMenu=index;
                            checkBox.setChecked(true);
                            checkBox.setVisibility(View.VISIBLE);
                        }
                    });

                }
            }

        });


        v.findViewById(R.id.MenuPick_btnClickSelectMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pickMenu==-1)    return;
                G.main.menuDataBase.SelectMenu(menu.get(pickMenu).name);
                Bundle bundle=new Bundle();
                bundle.putInt("MenuPictureUri",menu.get(pickMenu).pictureUri);
                bundle.putString("MenuName",menu.get(pickMenu).name);
                G.main.FragmentChange(5,bundle);
            }
        });

        v.findViewById(R.id.MenuPick_btnClickRandomMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                ArrayList<String> menuNameList=new ArrayList<>();
                for(MenuItem m : menu)
                    menuNameList.add(m.name);
                bundle.putStringArrayList("menuNameList",menuNameList);
                ArrayList<Integer> menuUriList=new ArrayList<>();
                for(MenuItem m : menu)
                    menuUriList.add(m.pictureUri);
                bundle.putIntegerArrayList("menuUriList",menuUriList);
                G.main.FragmentChange(4,bundle);

            }
        });

        return v;

    }

}


