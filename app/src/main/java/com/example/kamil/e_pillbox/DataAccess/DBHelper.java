package com.example.kamil.e_pillbox.DataAccess;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kamil.e_pillbox.DataAccess.tabela.Lek_interface;

public class DBHelper extends SQLiteOpenHelper {
    private final static int DB_VERSION=2;//domyślnie jest 1
    private final static String DB_NAME="BazaLekow.db";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+Lek_interface.TABLE_NAME
                +" ("
                +Lek_interface.Columns.LEK_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Lek_interface.Columns.LEK_NAZWA+" TEXT , "
                +Lek_interface.Columns.LEK_ILOSC+" TEXT , "
                +Lek_interface.Columns.LEK_DATA+" TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Lek_interface.TABLE_NAME);
        onCreate(db);
    }
}
//TODO DOROBIC USOWANIE ELEMENTU ORAZ AKTUALIZACJE