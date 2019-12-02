package com.factory.salmon.pickranmenu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MenuPickAlertDialog {

    RecyclerView recycler;

    int[] menuNumMaxResult=new int[3];
    int[] menuNumResult=new int[3];

    public MenuPickAlertDialog() {

        View v=G.main.getLayoutInflater().inflate(R.layout.dialog_pick_menu,null,false);
        recycler=v.findViewById(R.id.pickMenu_dialog_recycler);
        recycler.addItemDecoration(new DividerItemDecoration(G.main,DividerItemDecoration.VERTICAL));

        recycler.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v=G.main.getLayoutInflater().inflate(R.layout.item_menu_list_num,parent,false);
                ViewHolder vh=new ViewHolder(v);

                return vh;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

                final ViewHolder vh=(ViewHolder)holder;
                vh.txtMenuNum.setText(G.menuNumSort[position]);

                ArrayList<String> arr=new ArrayList<>();
                for(int i=0;i<G.menuNumMax[position]+1;i++) arr.add(i+"개");
                vh.spinnerMenuNum.setOnItemSelectedListener(null);
                vh.spinnerMenuNumMax.setOnItemSelectedListener(null);
                vh.spinnerMenuNumMax.setAdapter(new ArrayAdapter<>(G.main,R.layout.custom_simple_spinner_dropdown_item,arr));
                vh.spinnerMenuNumMax.setSelection(G.menuNumSelect[position]);
                final boolean[] isFirst={true};
                vh.spinnerMenuNumMax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        menuNumMaxResult[position]=i;

                        ArrayList<String> ar=new ArrayList<>();
                        for(int n=0;n<i+1;n++)    ar.add(n+"개");

                        vh.spinnerMenuNum.setAdapter(new ArrayAdapter<>(G.main,R.layout.custom_simple_spinner_dropdown_item,ar));

                        if(isFirst[0]) {
                            vh.spinnerMenuNum.setSelection(G.menuNum[position]);
                            isFirst[0] =false; return;
                        }
                        vh.spinnerMenuNum.setSelection(i);

                    }

                    @Override public void onNothingSelected(AdapterView<?> adapterView) { }
                });

                vh.spinnerMenuNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        menuNumResult[position]=i;
                    }

                    @Override public void onNothingSelected(AdapterView<?> adapterView) { }
                });

                vh.btnShowList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<MenuItem> menuList=G.main.menuDataBase.GetRankingList(position,vh.spinnerMenuNumMax.getSelectedItemPosition());
                        StringBuffer buffer=new StringBuffer();
                        buffer.append("다음 목록중에서 "+vh.spinnerMenuNum.getSelectedItem()+"가 선택됩니다.\n");
                        for(MenuItem menu : menuList)
                            buffer.append(menu.name+"\n");
                        new AlertDialog.Builder(G.main).setMessage(buffer.toString()).setPositiveButton("확인",null).create().show();
                    }
                });

            }

            @Override
            public int getItemCount() {
                return 3;
            }

            class ViewHolder extends RecyclerView.ViewHolder{

                TextView txtMenuNum;
                Spinner spinnerMenuNumMax;
                Spinner spinnerMenuNum;
                MaterialButton btnShowList;

                public ViewHolder(@NonNull View itemView) {
                    super(itemView);

                    txtMenuNum=itemView.findViewById(R.id.MenuList_Num_item_txt);
                    spinnerMenuNumMax=itemView.findViewById(R.id.MenuList_Num_item_spinnerMax);
                    spinnerMenuNum=itemView.findViewById(R.id.MenuList_Num_item_spinnerNum);
                    btnShowList=itemView.findViewById(R.id.MenuList_Num_item_btnShowList);

                }
            }
        });




        AlertDialog.Builder builder=new AlertDialog.Builder(G.main).setView(v);
        builder.setNeutralButton("CANCEL",null).setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Bundle bundle=new Bundle();
                bundle.putIntArray("MenuNumMaxResult",menuNumMaxResult);
                bundle.putIntArray("MenuNumResult",menuNumResult);
                G.main.FragmentChange(3,bundle);
            }
        });
        builder.create().show();


    }

}
