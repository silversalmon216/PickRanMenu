package com.factory.salmon.pickranmenu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
        String dataBase_key1="(menuName text not null, menuNum ubteger, OnOff integer";
        String dataBase_key2="(menuName, menuNum, OnOff";
        for(String a : context.getResources().getStringArray(R.array.taste)){
            dataBase_key1=dataBase_key1+", "+a+" integer";
            dataBase_key2=dataBase_key2+", "+a;
        }
        dataBase_key1+=")";
        dataBase_key2+=")";

        database_menu.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " " + dataBase_key1);

        for(String b : context.getResources().getStringArray(R.array.menu)){

            String dataBase_Value=" VALUES('" + b + "',0,1,";

            if(b.equals("김밥"))  dataBase_Value+="0, 0, 0, 1, 0, 1)";
            else if(b.equals("짬뽕")) dataBase_Value+="1, 0, 1, 0, 0, 0)";
            else if(b.equals("해장국")) dataBase_Value+="1, 0, 0, 1, 0, 1)";
            else if(b.equals("햄버거")) dataBase_Value+="0, 1, 1, 0, 1, 0)";
            else if(b.equals("피자")) dataBase_Value+="0, 1, 1, 0, 1, 0)";
            else if(b.equals("라면")) dataBase_Value+="1, 0, 1, 0, 1, 0)";

            database_menu.execSQL("INSERT INTO " + TABLE_NAME + " " + dataBase_key2 + dataBase_Value);
        }

        Cursor cursor=database_menu.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()){
            buffer.append(cursor.getString(0)+" "+cursor.getInt(1)+" "+cursor.getInt(2)+" "+cursor.getInt(3)+" "+cursor.getInt(4)+" "+cursor.getInt(5)+" "+cursor.getInt(6)+" "+cursor.getInt(7)+" "+cursor.getInt(8)+"\n\n");
        }
        new AlertDialog.Builder(context).setMessage(buffer.toString()).setPositiveButton("OK",null).create().show();

    }

    public void SelectMenu(String menu){
        Cursor cursor=database_menu.rawQuery("SELECT menuNum FROM " + TABLE_NAME + " WHERE menuName=?",new String[]{menu});
        cursor.moveToFirst();
        database_menu.execSQL("UPDATE " + TABLE_NAME + " SET menuNum=" + (cursor.getInt(0)+1) + " WHERE menuName=?",new String[]{menu});

        Cursor c=database_menu.rawQuery("SELECT menuNum FROM " + TABLE_NAME + " WHERE menuName=?",new String[]{menu});
        c.moveToFirst();
        new AlertDialog.Builder(context).setMessage(menu+" : "+c.getInt(0)).setPositiveButton("OK",null).create().show();
    }

    public void SwitchMenu(String menu){
        Cursor cursor=database_menu.rawQuery("SELECT OnOff FROM " + TABLE_NAME + " WHERE menuName=?",new String[]{menu});
        cursor.moveToFirst();
        int OnOff=2;
        OnOff= cursor.getInt(0)==0 ? 1 : 0;
        if(OnOff==2)    return;

        database_menu.execSQL("UPDATE "+TABLE_NAME + " SET OnOff=" + OnOff + " WHERE menuName=?",new String[]{menu});

        Cursor c=database_menu.rawQuery("SELECT OnOff FROM " + TABLE_NAME + " WHERE menuName=?",new String[]{menu});
        c.moveToFirst();
        new AlertDialog.Builder(context).setMessage(menu+" : "+c.getInt(0)).setPositiveButton("OK",null).create().show();
    }

}
