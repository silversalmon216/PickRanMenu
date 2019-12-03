package com.factory.salmon.pickranmenu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;

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
        String dataBase_key1="(menuName text not null, menuNum integer, menuNumRanking integer, menuRecentRanking integer, OnOff integer, searchKeyword text, pictureUri integer not null";
        String dataBase_key2="(menuName, menuNum, menuNumRanking, menuRecentRanking, OnOff, searchKeyword, pictureUri";
        for(String a : context.getResources().getStringArray(R.array.favor)){
            dataBase_key1=dataBase_key1+", "+a+" integer";
            dataBase_key2=dataBase_key2+", "+a;
        }
        dataBase_key1+=")";
        dataBase_key2+=")";

        database_menu.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " " + dataBase_key1);

        for(String b : context.getResources().getStringArray(R.array.menu)){

            String dataBase_Value=" VALUES('" + b + "', 0, 11, 11, 1,";

            if(b.equals("김밥"))  dataBase_Value=dataBase_Value+"'김밥&분식', '"+R.drawable.menu_kimbab +"', 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("고기"))  dataBase_Value=dataBase_Value+"'고기', '"+R.drawable.menu_meat +"', 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("감자탕"))  dataBase_Value=dataBase_Value+"'감자탕', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1)";
            else if(b.equals("덮밥"))  dataBase_Value=dataBase_Value+"'덮밥', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("돈가스"))  dataBase_Value=dataBase_Value+"'돈가스&분식', '"+R.drawable.menu_dongas +"', 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1)";
            else if(b.equals("도시락"))  dataBase_Value=dataBase_Value+"'도시락', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("라면"))  dataBase_Value=dataBase_Value+"'라면', '"+R.drawable.menu_ramyun +"', 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0)";
            else if(b.equals("부대찌개"))  dataBase_Value=dataBase_Value+"'부대찌개', '"+R.drawable.menu_unknown +"', 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0)";
            else if(b.equals("보쌈"))  dataBase_Value=dataBase_Value+"'보쌈', '"+R.drawable.menu_suyuk +"', 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("빵"))  dataBase_Value=dataBase_Value+"'빵', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0)";
            else if(b.equals("생선구이"))  dataBase_Value=dataBase_Value+"'생선구이', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("양꼬치"))  dataBase_Value=dataBase_Value+"'양꼬치', '"+R.drawable.menu_unknown +"', 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("양장피"))  dataBase_Value=dataBase_Value+"'양장피&중국집', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0)";
            else if(b.equals("오무라이스"))  dataBase_Value=dataBase_Value+"'오무라이스&분식', '"+R.drawable.menu_omurice +"', 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("제육볶음"))  dataBase_Value=dataBase_Value+"'제육&분식', '"+R.drawable.menu_unknown +"', 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("죽"))  dataBase_Value=dataBase_Value+"'죽', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("족발"))  dataBase_Value=dataBase_Value+"'족발', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("찜닭"))  dataBase_Value=dataBase_Value+"'찜닭', '"+R.drawable.menu_unknown +"', 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1)";
            else if(b.equals("짬뽕"))  dataBase_Value=dataBase_Value+"'짬뽕&중국집', '"+R.drawable.menu_jjambbong +"', 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0)";
            else if(b.equals("자장면"))  dataBase_Value=dataBase_Value+"'자장면&중국집', '"+R.drawable.menu_jjajang_noodle +"', 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0)";
            else if(b.equals("치킨"))  dataBase_Value=dataBase_Value+"'치킨', '"+R.drawable.menu_unknown +"', 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1)";
            else if(b.equals("카레"))  dataBase_Value=dataBase_Value+"'카레&분식', '"+R.drawable.menu_unknown +"', 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("탕수육"))  dataBase_Value=dataBase_Value+"'탕수육&중국집', '"+R.drawable.menu_unknown +"', 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1)";
            else if(b.equals("회"))  dataBase_Value=dataBase_Value+"'회', '"+R.drawable.menu_raw_salmon +"', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("해장국"))  dataBase_Value=dataBase_Value+"'해장국', '"+R.drawable.menu_haejangguk +"', 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0)";
            else if(b.equals("햄버거"))  dataBase_Value=dataBase_Value+"'햄버거', '"+R.drawable.menu_unknown +"', 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1)";
            else if(b.equals("피자"))  dataBase_Value=dataBase_Value+"'피자', '"+R.drawable.menu_unknown +"', 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1)";
            else if(b.equals("파스타"))  dataBase_Value=dataBase_Value+"'파스타', '"+R.drawable.menu_unknown +"', 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0)";
            else if(b.equals("꽃게"))  dataBase_Value=dataBase_Value+"'꽃게', '"+R.drawable.menu_crab +"', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)";
            else if(b.equals("잔치국수"))  dataBase_Value=dataBase_Value+"'잔치국수', '"+R.drawable.menu_janchi_noodle +"', 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0)";
            else if(b.equals("순두부찌개"))  dataBase_Value=dataBase_Value+"'순두부찌개', '"+R.drawable.menu_unknown +"', 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0)";
//            else if(b.equals(""))  dataBase_Value=dataBase_Value+"'', '"+R.drawable.menu_crab +"', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)";

            database_menu.execSQL("INSERT INTO " + TABLE_NAME + dataBase_key2 + dataBase_Value);
        }

    }

    public void SelectMenu(String menu){
        Cursor cursor=database_menu.rawQuery("SELECT menuNum, menuNumRanking, menuRecentRanking FROM " + TABLE_NAME + " WHERE menuName=?",new String[]{menu});
        cursor.moveToFirst();
        database_menu.execSQL("UPDATE " + TABLE_NAME + " SET menuNum=" + (cursor.getInt(0)+1) + " WHERE menuName=?",new String[]{menu});

        if(cursor.getInt(1)!=1) {
            Cursor cursor1 = database_menu.rawQuery("SELECT menuName FROM " + TABLE_NAME + " WHERE menuName!=? AND menuNumRanking=? AND menuNum=?", new String[]{ menu, (cursor.getInt(1)-1)+"", (cursor.getInt(0)+1)+"" });
            Cursor cursor2 = database_menu.rawQuery("SELECT menuName FROM " + TABLE_NAME + " WHERE menuName!=? AND menuNumRanking=? AND menuNum=?", new String[]{ menu, cursor.getInt(1)+"", cursor.getInt(0)+"" });
            Cursor cursor3=database_menu.rawQuery("SELECT menuName, menuNumRanking FROM " + TABLE_NAME + " WHERE menuName!=? AND menuNumRanking<11 AND menuNumRanking>=?",new String[]{ menu, cursor.getInt(1)+"" });

            if(cursor1.moveToFirst() || cursor2.moveToFirst()) {
                if (!cursor1.moveToFirst() && cursor2.moveToFirst())
                    while (cursor3.moveToNext())
                        database_menu.execSQL("UPDATE " + TABLE_NAME + " SET menuNumRanking=" + (cursor3.getInt(1) + 1) + " WHERE menuName=?", new String[]{cursor3.getString(0)});
                else if (cursor1.moveToFirst() && !cursor2.moveToFirst())
                    while (cursor3.moveToNext())
                        database_menu.execSQL("UPDATE " + TABLE_NAME + " SET menuNumRanking=" + (cursor3.getInt(1) - 1) + " WHERE menuName=?", new String[]{cursor3.getString(0)});

                database_menu.execSQL("UPDATE " + TABLE_NAME + " SET menuNumRanking=" + (cursor.getInt(1) - 1) + " WHERE menuName=?", new String[]{menu});
            }
        }

        if(cursor.getInt(2)!=1){
            Cursor cursor1=database_menu.rawQuery("SELECT menuName, menuRecentRanking FROM " + TABLE_NAME + " WHERE menuRecentRanking<?",new String[]{ cursor.getInt(2)+"" });
            while(cursor1.moveToNext())
                database_menu.execSQL("UPDATE " + TABLE_NAME + " SET menuRecentRanking=" + (cursor1.getInt(1)+1) + " WHERE menuName=?",new String[]{ cursor1.getString(0) });
            database_menu.execSQL("UPDATE " + TABLE_NAME + " SET menuRecentRanking=1 WHERE menuName=?",new String[]{ menu });
        }

    }

    public void SwitchMenu(String menu, boolean isMakeOn){
        int isOn= isMakeOn ? 1 : 0;
        database_menu.execSQL("UPDATE "+TABLE_NAME + " SET OnOff=" + isOn + " WHERE menuName=?",new String[]{menu});
        GetMaxNum();
    }

    public void GetMaxNum(){

        Cursor cursor=database_menu.rawQuery("SELECT menuName FROM " + TABLE_NAME + " WHERE OnOff=1",null);
        int num=0;
        while(cursor.moveToNext())
            num++;
        for(int i=0;i<3;i++)
            if(num<G.menuNumMax[i]) {
                G.menuNumMax[i] = num;
                if(num<G.menuNumSelect[i])
                    G.menuNumSelect[i]=num;
                if(num<G.menuNum[i])
                    G.menuNum[i]=num;
            }
        else if(G.menuNumMax[i]<11)
            G.menuNumMax[i]=num>10 ? 10 : num;

    }

    public ArrayList<MenuItem> GetRankingList(int index, int indexLength){

        ArrayList<MenuItem> menuRanking=new ArrayList<>();
        String select="";
        switch(index){
            case 0:
                select="SELECT menuName, menuNumRanking, pictureUri FROM " + TABLE_NAME + " WHERE menuNumRanking=? AND OnOff=?";
                break;
            case 1:
                select="SELECT menuName, menuRecentRanking, pictureUri FROM " + TABLE_NAME + " WHERE menuRecentRanking=? AND OnOff=?";
                break;
            case 2:
                select="SELECT menuName, pictureUri FROM " + TABLE_NAME + " WHERE OnOff=1";
        }

        if(index==2){
            Cursor cursor=database_menu.rawQuery(select,null);
            while(cursor.moveToNext())
                menuRanking.add(new MenuItem(cursor.getString(0),cursor.getInt(1),0));
            Collections.shuffle(menuRanking);
            ArrayList<MenuItem> newList=new ArrayList<>();
            newList.addAll(menuRanking.subList(0,indexLength));
            return newList;
        }

        for(int i=1;i<11;i++){
            Cursor cursor=database_menu.rawQuery(select,new String[]{ i+"", "1" });
            while(cursor.moveToNext())
                menuRanking.add(new MenuItem(cursor.getString(0),cursor.getInt(2),cursor.getInt(1)));
            if(menuRanking.size()>=indexLength) break;
        }

        if(menuRanking.size()<indexLength){
            Cursor cursor=database_menu.rawQuery(select,new String[]{ "11", "1" });
            while(cursor.moveToNext())
                menuRanking.add(new MenuItem(cursor.getString(0), cursor.getInt(2), 11));
        }

        if(menuRanking.size()>indexLength){
            ArrayList<MenuItem> menuSecondRanking=new ArrayList<>();

            while(menuRanking.get(0).ranking!=menuRanking.get(menuRanking.size()-1).ranking)
                menuSecondRanking.add(menuRanking.remove(0));

            Collections.shuffle(menuRanking);
            menuSecondRanking.addAll(menuRanking.subList(0,indexLength-menuSecondRanking.size()));

            menuRanking=menuSecondRanking;

        }

        return menuRanking;
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
