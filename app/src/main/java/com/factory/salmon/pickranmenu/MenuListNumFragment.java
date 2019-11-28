package com.factory.salmon.pickranmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuListNumFragment extends Fragment {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_menu_list_recycler,container,false);
        recyclerView=v.findViewById(R.id.MenuList_recycler);
        recyclerView.addItemDecoration(new DividerItemDecoration(G.main,DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v=getActivity().getLayoutInflater().inflate(R.layout.item_menu_list_num,parent,false);
                VH holder=new VH(v);

                return holder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

                final VH vh=(VH)holder;
                vh.txtMenuNum.setText(G.menuNumSort[position]);

                ArrayList<String> arr=new ArrayList<>();
                for(int i=0;i<G.menuNumMax[position]+1;i++){ arr.add(i+"개"); }
                vh.spinnerMenuNumMax.setOnItemSelectedListener(null);
                vh.spinnerMenuNum.setOnItemSelectedListener(null);
                vh.spinnerMenuNumMax.setAdapter(new ArrayAdapter<>(G.main,R.layout.custom_simple_spinner_dropdown_item,arr));
                vh.spinnerMenuNumMax.setSelection(G.menuNumSelect[position]);
                final boolean[] isFirst = {true};
                vh.spinnerMenuNumMax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        String key="";
                        if(position==0) key=G.main.PREFERENCES_KEY_NUM_RECENT_SELECT;
                        else if(position==1)    key=G.main.PREFERENCES_KEY_NUM_FAVORITE_SELECT;
                        else if(position==2)    key=G.main.PREFERENCES_KEY_NUM_SERVER_SELECT;
                        G.main.SetMenuNum(key,i);
                        G.menuNumSelect[position]=i;

                        ArrayList<String> ar=new ArrayList<>();
                        for(int j=0;j<i+1;j++)    ar.add(j+"개");

                        vh.spinnerMenuNum.setAdapter(new ArrayAdapter<>(G.main,R.layout.custom_simple_spinner_dropdown_item,ar));

                        if(isFirst[0]) {
                            vh.spinnerMenuNum.setSelection(G.menuNum[position]);
                            isFirst[0] =false; return;
                        }
                        vh.spinnerMenuNum.setSelection(i);

                    }

                    @Override public void onNothingSelected(AdapterView<?> adapterView) { }
                });

//                vh.spinnerMenuNum.setAdapter(new ArrayAdapter<>(G.main,R.layout.custom_simple_spinner_dropdown_item,arr));
//                vh.spinnerMenuNum.setSelection(menuNum[position]);
                vh.spinnerMenuNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String key="";
                        if(position==0)    key=G.main.PREFERENCES_KEY_NUM_RECENT;
                        else if(position==1)   key=G.main.PREFERENCES_KEY_NUM_FAVORITE;
                        else if(position==2)   key=G.main.PREFERENCES_KEY_NUM_SERVER;
                        G.main.SetMenuNum(key,i);
                        G.menuNum[position]=i;
                    }

                    @Override public void onNothingSelected(AdapterView<?> adapterView) { }
                });


            }

            @Override
            public int getItemCount() {
                return 3;
            }

            class VH extends RecyclerView.ViewHolder{

                TextView txtMenuNum;
                Spinner spinnerMenuNumMax;
                Spinner spinnerMenuNum;

                public VH(@NonNull View itemView) {
                    super(itemView);

                    txtMenuNum=itemView.findViewById(R.id.MenuList_Num_item_txt);
                    spinnerMenuNumMax=itemView.findViewById(R.id.MenuList_Num_item_spinnerMax);
                    spinnerMenuNum=itemView.findViewById(R.id.MenuList_Num_item_spinnerNum);

                }
            }

        });

        return v;
    }

    public void ClickMenuListSample(View v){

    }


}
