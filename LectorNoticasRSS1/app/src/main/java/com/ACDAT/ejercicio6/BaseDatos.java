package com.ACDAT.ejercicio6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by melqui on 2/27/18.
 */

public class BaseDatos extends SQLiteOpenHelper {

    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE noticias(label text primary key,link text)");
        sqLiteDatabase.execSQL("INSERT INTO noticias(label,link) VALUES(' -- ','http://ep00.epimg.net/rss/tags/ultimas_noticias.xml')");
        sqLiteDatabase.execSQL("INSERT INTO noticias(label,link) VALUES('El reforma','http://www.reforma.com/rss/ciencia.xml')");
        sqLiteDatabase.execSQL("INSERT INTO noticias(label,link) VALUES('TV azteca','http://www.aztecanoticias.com.mx/rss/mexico.xml')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists noticias");
        sqLiteDatabase.execSQL("CREATE TABLE noticias(label text primary key,link text)");
    }


}
