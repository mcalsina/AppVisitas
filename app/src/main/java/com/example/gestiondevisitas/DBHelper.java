package com.example.gestiondevisitas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME ="Visitas.db";

    public DBHelper(Context context) {
        super(context, "Visitas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create table usuarios(username TEXT primary key, password TEXT)");
        MyDB.execSQL("create table clientes(numeroDocumento INTEGER PRIMARY KEY, nombres TEXT, apellidoPaterno TEXT, apellidoMaterno TEXT, telefono TEXT, comentarios TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

        MyDB.execSQL("drop table if exists usuarios");
        MyDB.execSQL("drop table if exists clientes");
    }
    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put( "username", username);
        contentValues.put( "password", password);
        long result = MyDB.insert("usuarios",null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from usuarios where username = ?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from usuarios where username = ? and password = ?", new String[] {username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
