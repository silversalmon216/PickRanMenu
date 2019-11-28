package com.factory.salmon.pickranmenu;

import android.app.AlertDialog;
import android.content.Context;
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


//        Cursor cur=database_menu.rawQuery("SELECT menuName,pictureUri FROM " + TABLE_NAME,null);
//        cur.moveToFirst();
//        while(cur.moveToNext()) {
//            new AlertDialog.Builder(G.main).setMessage(cur.getString(0)+" - "+cur.getInt(0)).setPositiveButton("OK",null).create().show();
//        }

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

            String dataBase_Value=" VALUES('" + b + "', 0, null, null, 1,";

            if(b.equals("김밥"))  dataBase_Value=dataBase_Value+"'김밥&분식', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("고기"))  dataBase_Value=dataBase_Value+"'고기', '"+R.drawable.menu_unknown +"', 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("감자탕"))  dataBase_Value=dataBase_Value+"'감자탕', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1)";
            else if(b.equals("덮밥"))  dataBase_Value=dataBase_Value+"'덮밥', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("돈가스"))  dataBase_Value=dataBase_Value+"'돈가스&분식', '"+R.drawable.menu_unknown +"', 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1)";
            else if(b.equals("도시락"))  dataBase_Value=dataBase_Value+"'도시락', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("라면"))  dataBase_Value=dataBase_Value+"'라면', '"+R.drawable.menu_unknown +"', 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0)";
            else if(b.equals("부대찌개"))  dataBase_Value=dataBase_Value+"'부대찌개', '"+R.drawable.menu_unknown +"', 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0)";
            else if(b.equals("보쌈"))  dataBase_Value=dataBase_Value+"'보쌈', '"+R.drawable.menu_unknown +"', 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("빵"))  dataBase_Value=dataBase_Value+"'빵', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0)";
            else if(b.equals("생선구이"))  dataBase_Value=dataBase_Value+"'생선구이', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("양꼬치"))  dataBase_Value=dataBase_Value+"'양꼬치', '"+R.drawable.menu_unknown +"', 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("양장피"))  dataBase_Value=dataBase_Value+"'양장피&중국집', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0)";
            else if(b.equals("오무라이스"))  dataBase_Value=dataBase_Value+"'오무라이스&분식', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("제육볶음"))  dataBase_Value=dataBase_Value+"'제육&분식', '"+R.drawable.menu_unknown +"', 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("죽"))  dataBase_Value=dataBase_Value+"'죽', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("족발"))  dataBase_Value=dataBase_Value+"'족발', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("찜닭"))  dataBase_Value=dataBase_Value+"'찜닭', '"+R.drawable.menu_unknown +"', 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1)";
            else if(b.equals("짬뽕"))  dataBase_Value=dataBase_Value+"'짬뽕&중국집', '"+R.drawable.menu_unknown +"', 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0)";
            else if(b.equals("자장면"))  dataBase_Value=dataBase_Value+"'자장면&중국집', '"+R.drawable.menu_unknown +"', 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0)";
            else if(b.equals("치킨"))  dataBase_Value=dataBase_Value+"'치킨', '"+R.drawable.menu_unknown +"', 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1)";
            else if(b.equals("카레"))  dataBase_Value=dataBase_Value+"'카레&분식', '"+R.drawable.menu_unknown +"', 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0)";
            else if(b.equals("탕수육"))  dataBase_Value=dataBase_Value+"'탕수육&중국집', '"+R.drawable.menu_unknown +"', 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1)";
            else if(b.equals("회"))  dataBase_Value=dataBase_Value+"'회', '"+R.drawable.menu_unknown +"', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)";
            else if(b.equals("해장국"))  dataBase_Value=dataBase_Value+"'해장국', '"+R.drawable.menu_unknown +"', 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0)";
            else if(b.equals("햄버거"))  dataBase_Value=dataBase_Value+"'햄버거', '"+R.drawable.menu_unknown +"', 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1)";
            else if(b.equals("피자"))  dataBase_Value=dataBase_Value+"'피자', '"+R.drawable.menu_unknown +"', 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1)";
            else if(b.equals("파스타"))  dataBase_Value=dataBase_Value+"'파스타', '"+R.drawable.menu_unknown +"', 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0)";

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
    }

    public ArrayList[] GetRankingList(int index, int indexLength){

        ArrayList<MenuItem> menuRanking=new ArrayList<>();
        String select="";
        switch(index){
            case 1:
                select="SELECT menuName, menuNumRanking, pictureUri FROM " + TABLE_NAME + " WHERE menuNumRanking=?";
                break;
            case 2:
                select="SELECT menuName, menuRecentRanking, pictureUri FROM " + TABLE_NAME + " WHERE menuRecentRanking=?";
                break;
        }

        for(int i=1;i<11;i++){
            Cursor cursor=database_menu.rawQuery(select,new String[]{i+""});
            while(cursor.moveToNext())
                menuRanking.add(new MenuItem(cursor.getString(0),cursor.getInt(2),cursor.getInt(1)));
            if(menuRanking.size()>=indexLength) break;
        }

        if(menuRanking.size()<indexLength){
            Cursor cursor=database_menu.rawQuery(select,new String[]{"null"});
            while(cursor.moveToNext()) {
                new AlertDialog.Builder(G.main).setMessage(cursor.getString(0)+" "+cursor.getInt(2)+" "+cursor.getInt(1)).setPositiveButton("OK",null).create().show();
                menuRanking.add(new MenuItem(cursor.getString(0), cursor.getInt(2), cursor.getInt(1)));
            }
        }

        StringBuffer buffer=new StringBuffer();
        buffer.append("랭킹목록\n"+select+"\n");
        for(MenuItem s : menuRanking)
            buffer.append(s.name+" "+s.ranking+" "+s.pictureUri+"\n");
        new AlertDialog.Builder(G.main).setMessage(buffer.toString()).setPositiveButton("OK",null).create().show();



        return null;
    }

    /*public ArrayList[] GetRankingList(int indexLength){
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
             menuUriResult.add(menuUri.get(v));
         }

         Random random=new Random();
         int range=samenum+samerange;
         for(int n=0;n<samenum;n++){
             int index=random.nextInt(range--);
             menuNameResult.add(menuName.remove(indexLength-samenum+index+1));
             menuUriResult.add(menuUri.remove(indexLength-samenum+index+1));
         }

         return new ArrayList[]{menuNameResult,menuUriResult};
    }*/

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
