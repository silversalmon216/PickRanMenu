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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuPickAlertDialog {

    RecyclerView recycler;

    int[] menuNumMaxResult=new int[3];
    int[] menuNumResult=new int[3];

    public MenuPickAlertDialog() {

        View v=G.main.getLayoutInflater().inflate(R.layout.dialog_pick_menu,null,false);
        recycler=v.findViewById(R.id.pickMenu_dialog_recycler);

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

            }

            @Override
            public int getItemCount() {
                return 3;
            }

            class ViewHolder extends RecyclerView.ViewHolder{

                TextView txtMenuNum;
                Spinner spinnerMenuNumMax;
                Spinner spinnerMenuNum;

                public ViewHolder(@NonNull View itemView) {
                    super(itemView);

                    txtMenuNum=itemView.findViewById(R.id.MenuList_Num_item_txt);
                    spinnerMenuNumMax=itemView.findViewById(R.id.MenuList_Num_item_spinnerMax);
                    spinnerMenuNum=itemView.findViewById(R.id.MenuList_Num_item_spinnerNum);

                }
            }
        });




        AlertDialog.Builder builder=new AlertDialog.Builder(G.main).setView(v);
        builder.setNegativeButton("CANCEL",null).setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Bundle bundle=new Bundle();
                bundle.putIntArray("menuNumMaxResult",menuNumMaxResult);
                bundle.putIntArray("menuNumResult",menuNumResult);
                G.main.FragmentChange(3,bundle);
            }
        });
        builder.create().show();


    }

}
