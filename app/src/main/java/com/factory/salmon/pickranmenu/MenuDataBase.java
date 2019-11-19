package com.factory.salmon.pickranmenu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Random;

public class MenuDataBase {

    private final static String DB_NAME="menuDB.db";
    private final static String TABLE_NAME="menu";

    SQLiteDatabase database_menu;

    Context context;

    public MenuDataBase(Context context) {

        this.context=context;
        database_menu=context.openOrCreateDatabase(DB_NAME,Context.MODE_PRIVATE,null);

    }

    public void InsertMenu(){
        String dataBase_key1="(menuName text not null, pictureUri integer not null, menuNum ubteger, OnOff integer, searchKeyword text";
        String dataBase_key2="(menuName, pictureUri, menuNum, OnOff, searchKeyword";
        for(String a : context.getResources().getStringArray(R.array.favor)){
            dataBase_key1=dataBase_key1+", "+a+" integer";
            dataBase_key2=dataBase_key2+", "+a;
        }
        dataBase_key1+=")";
        dataBase_key2+=")";

        database_menu.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " " + dataBase_key1);

        for(String b : context.getResources().getStringArray(R.array.menu)){

            String dataBase_Value=" VALUES('" + b + "', '" + R.drawable.picture + "',0,1,";

            if(b.equals("김밥"))  dataBase_Value+="'김밥&분식', 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("고기"))  dataBase_Value+="'고기', 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("감자탕"))  dataBase_Value+="'감자탕', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1)";
            else if(b.equals("덮밥"))  dataBase_Value+="'덮밥', 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("돈가스"))  dataBase_Value+="'돈가스&분식', 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1)";
            else if(b.equals("도시락"))  dataBase_Value+="'도시락', 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("라면"))  dataBase_Value+="'라면', 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0)";
            else if(b.equals("부대찌개"))  dataBase_Value+="'부대찌개', 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0)";
            else if(b.equals("보쌈"))  dataBase_Value+="'보쌈', 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("빵"))  dataBase_Value+="'빵', 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0)";
            else if(b.equals("생선구이"))  dataBase_Value+="'생선구이', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("양꼬치"))  dataBase_Value+="'양꼬치', 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("양장피"))  dataBase_Value+="'양장피&중국집', 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0)";
            else if(b.equals("오무라이스"))  dataBase_Value+="'오무라이스&분식', 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("제육볶음"))  dataBase_Value+="'제육&분식', 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("죽"))  dataBase_Value+="'죽', 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("족발"))  dataBase_Value+="'족발', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("찜닭"))  dataBase_Value+="'찜닭', 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1)";
            else if(b.equals("짬뽕"))  dataBase_Value+="'짬뽕&중국집', 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0)";
            else if(b.equals("자장면"))  dataBase_Value+="'자장면&중국집', 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0)";
            else if(b.equals("치킨"))  dataBase_Value+="'치킨', 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1)";
            else if(b.equals("카레"))  dataBase_Value+="'카레&분식', 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("탕수육"))  dataBase_Value+="'탕수육&중국집', 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1)";
            else if(b.equals("회"))  dataBase_Value+="'회', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("해장국"))  dataBase_Value+="'해장국', 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0)";
            else if(b.equals("햄버거"))  dataBase_Value+="'햄버거', 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1)";
            else if(b.equals("피자"))  dataBase_Value+="'피자', 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1)";
            else if(b.equals("파스타"))  dataBase_Value+="'파스타', 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0)";

            database_menu.execSQL("INSERT INTO " + TABLE_NAME + dataBase_key2 + dataBase_Value);
        }

    }

    public void SelectMenu(String menu){
        Cursor cursor=database_menu.rawQuery("SELECT menuNum FROM " + TABLE_NAME + " WHERE menuName=?",new String[]{menu});
        cursor.moveToFirst();
        database_menu.execSQL("UPDATE " + TABLE_NAME + " SET menuNum=" + (cursor.getInt(0)+1) + " WHERE menuName=?",new String[]{menu});

        Cursor c=database_menu.rawQuery("SELECT menuNum FROM " + TABLE_NAME + " WHERE menuName=?",new String[]{menu});
        c.moveToFirst();
        new AlertDialog.Builder(context).setMessage(menu+" : "+c.getInt(0)).setPositiveButton("OK",null).create().show();
    }

    public void SwitchMenu(String menu, boolean isMakeOn){
        int isOn= isMakeOn ? 1 : 0;

        database_menu.execSQL("UPDATE "+TABLE_NAME + " SET OnOff=" + isOn + " WHERE menuName=?",new String[]{menu});

        Cursor c=database_menu.rawQuery("SELECT OnOff FROM " + TABLE_NAME + " WHERE menuName=?",new String[]{menu});
        c.moveToFirst();
//        new AlertDialog.Builder(context).setMessage(menu+" : "+c.getInt(0)).setPositiveButton("OK",null).create().show();
    }

    public ArrayList[] GetRankingList(int indexLength){
        Cursor cursor=database_menu.rawQuery("SELECT menuName, MenuNum, pictureUri FROM " + TABLE_NAME,null);

        ArrayList<Integer> menuNum=new ArrayList<>();
        ArrayList<String> menuName=new ArrayList<>();
        ArrayList<Integer> menuUri=new ArrayList<>();

        while(cursor.moveToNext()){
            int i=0;
            for(;i<menuNum.size();i++){
                if(cursor.getInt(1)<menuNum.get(i))
                    break;
            }
            menuName.add(i,cursor.getString(0));
            menuNum.add(i,cursor.getInt(1));
            menuUri.add(i,cursor.getInt(2));
        }

         if(menuNum.get(indexLength)!=menuNum.get(indexLength+1)) {
             return new ArrayList[]{new ArrayList(menuName.subList(0,indexLength+1)), new ArrayList(menuUri.subList(0,indexLength+1))};
         }


         int samenum=0;
         int samerange=0;
         ArrayList<String> menuNameResult=new ArrayList<>();
         ArrayList<Integer> menuUriResult=new ArrayList<>();

         for(int j=indexLength;j>=0;j--){
             if(menuNum.get(j)!=menuNum.get(indexLength))   break;
             samenum++;
         }

         while(menuNum.get(indexLength)==menuNum.get(indexLength+samerange)){
             samerange++;
             if(indexLength+samerange+1>=menuNum.size())    break;
         }

         for(int v=0;v<indexLength-samenum;v++){
             menuNameResult.add(menuName.get(v));
             menuUriResult.add(menuNum.get(v));
         }

         Random random=new Random();
         int range=samenum+samerange;
         for(int n=0;n<samenum;n++){
             int index=random.nextInt(range--);
             menuNameResult.add(menuName.remove(indexLength-samenum+index+1));
             menuUriResult.add(menuNum.remove(indexLength-samenum+index+1));
         }

         return new ArrayList[]{menuNameResult,menuUriResult};
    }

    public void SwitchFavor(String favor, boolean isMakeOn){
        Cursor cursor=database_menu.rawQuery("SELECT menuName, OnOff FROM " + TABLE_NAME + " WHERE " + favor + "=?",new String[]{"1"});
        while(cursor.moveToNext()){
            SwitchMenu(cursor.getString(0),isMakeOn);
        }
    }

    public ArrayList<Integer> GetArrayList(String select){

        Cursor cursor=database_menu.rawQuery("SELECT " + select + " FROM " + TABLE_NAME,null);
        ArrayList arr=new ArrayList<>();

        String str="";
        while(cursor.moveToNext()){
            arr.add(cursor.getInt(0));
            str+=cursor.getInt(0)+"\n";
        }

        return arr;

    }

}
