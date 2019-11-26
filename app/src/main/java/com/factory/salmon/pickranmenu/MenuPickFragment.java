package com.factory.salmon.pickranmenu;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import java.util.Random;

public class MenuPickFragment extends Fragment {

    RecyclerView recycler;
    int[] menuNumMaxResult;
    int[] menuNumResult;
    ArrayList<String> menuName;
    ArrayList<Integer> menuUri;
    ArrayList<Integer> menuIndex=new ArrayList<>();
    ArrayList<MaterialCheckBox> checkBoxArray=new ArrayList<>();

    int pickMenu=-1;

    public MenuPickFragment(Bundle bundle){
        menuNumMaxResult=bundle.getIntArray("menuNumMaxResult");
        menuNumResult=bundle.getIntArray("menuNumResult");

        ArrayList[] menu=G.main.menuDataBase.GetRankingList(menuNumMaxResult[0]);
        menuName=menu[0];
        menuUri=menu[1];

        Random random=new Random();
        for(int i=menuNumResult[0];i>0;i--){
            int index=random.nextInt(menuNumMaxResult[0]);

            boolean isSame=false;
            for(int num : menuIndex){
                if(num==index){
                    isSame=true;
                    break;
                }
            }
            if(isSame){ i++;    continue; }
            menuIndex.add(index);

        }

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
                Random random=new Random();

                int index=random.nextInt(menuNumResult[0]);

                vh.menuName.setText(menuName.get(index));
                Glide.with(G.main).load(menuUri.get(index)).into(vh.menuImg);
//                new AlertDialog.Builder(G.main).setMessage(menuName.get(index)+menuUri.get(index)).setPositiveButton("OK",null).create().show();
//                Picasso.get().load(menuUri.get(index)).into(vh.menuImg);

                if(index==pickMenu) vh.checkBox.setChecked(true);
                else    vh.checkBox.setChecked(false);

//                vh.checkBox.setOnTouchListener(new View.OnTouchListener() {
//                    @Override public boolean onTouch(View v, MotionEvent event) {
//                        MaterialCheckBox check=(MaterialCheckBox)v;
//                        check.setChecked(!check.isChecked());
//                        return true;
//                    }
//                });
//                vh.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { buttonView.setChecked(false); }
//                });





            }

            @Override
            public int getItemCount() {
                return menuNumResult[0];
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
                            if(pickMenu!=-1)    checkBoxArray.get(pickMenu).setChecked(false);
                            pickMenu=index;
                            checkBox.setChecked(true);
                        }
                    });

                }

//                @Override
//                public void onClick(View v) {
//                    pickMenu=getAdapterPosition();
//                    checkBoxArray.get(pickMenu).setChecked(false);
//                    checkBox.setChecked(true);
//                }
            }

        });

//        recycler.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
//            @Override
//            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//
//
//                return true;
//            }
//        });

        return v;

    }


}


