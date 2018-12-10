package com.example.kamil.e_pillbox.DataAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.kamil.e_pillbox.DataAccess.tabela.Lek_interface;
import com.example.kamil.e_pillbox.pojo.Lekarstwo;


import java.util.LinkedList;
import java.util.List;



public class LekarstwoDAO {
    private DBHelper dbHelper;
    public LekarstwoDAO(Context context){
        dbHelper=new DBHelper(context);
    }


    public void insertLek(final Lekarstwo lek){
        ContentValues cv=new ContentValues();
        cv.put(Lek_interface.Columns.LEK_NAZWA,lek.getNazwaLeku());
        cv.put(Lek_interface.Columns.LEK_ILOSC,lek.getIloscOpakowanie());
        cv.put(Lek_interface.Columns.LEK_DATA,lek.getDataWaznosci());
        dbHelper.getWritableDatabase().insert(Lek_interface.TABLE_NAME,null,cv);
    }

    public void deleteLek(final Lekarstwo lek){
        dbHelper.getWritableDatabase().delete(Lek_interface.TABLE_NAME," "+Lek_interface.Columns.LEK_ID+" = ? ",new String[]{lek.getId().toString()});
    }
    public void updateLekByCheckBox(final Lekarstwo lek){
        //Log.d("epilbox","updateLekByCheckbox "+lek.getNazwaLeku()+" "+lek.getIsZazyte()+" id: "+lek.getId().toString());

        ContentValues cv=new ContentValues();
        cv.put(Lek_interface.Columns.LEK_ZAZYCIE,lek.getIsZazyte());
        dbHelper.getWritableDatabase().update(Lek_interface.TABLE_NAME,cv," "+Lek_interface.Columns.LEK_ID+" = ? ",new String[]{lek.getId().toString()});


    }



    public List<Lekarstwo> getLekByNazwa(final String nazwaLeku){
        List<Lekarstwo> lekarstwa=new LinkedList<Lekarstwo>();
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery("SELECT * FROM "+Lek_interface.TABLE_NAME+" WHERE "+Lek_interface.Columns.LEK_NAZWA+" LIKE "+"'%"+nazwaLeku+"%' ORDER BY "+Lek_interface.Columns.LEK_ZAZYCIE+" DESC",null);
        while (cursor.moveToNext()){
            lekarstwa.add(mapCursorToLek(cursor));
        }
        return lekarstwa;
    }

    public List<Lekarstwo> getAllData(){
        List<Lekarstwo> lekarstwa=new LinkedList<Lekarstwo>();

        String[] columns={Lek_interface.Columns.LEK_ID,Lek_interface.Columns.LEK_NAZWA,Lek_interface.Columns.LEK_ILOSC,Lek_interface.Columns.LEK_DATA,Lek_interface.Columns.LEK_ZAZYCIE};//zazycie dopisane

        Cursor cursor=dbHelper.getReadableDatabase().query(Lek_interface.TABLE_NAME,columns,null,null,null,null,Lek_interface.Columns.LEK_ZAZYCIE+" DESC");


            while (cursor.moveToNext()){
                lekarstwa.add(mapCursorToLek(cursor));
            }

        return lekarstwa;
    }




    private Lekarstwo mapCursorToLek(final Cursor cursor){
        int idColumnId=cursor.getColumnIndex(Lek_interface.Columns.LEK_ID);
        int nazwaColumnId=cursor.getColumnIndex(Lek_interface.Columns.LEK_NAZWA);
        int iloscColumnId=cursor.getColumnIndex(Lek_interface.Columns.LEK_ILOSC);
        int dataColumnId=cursor.getColumnIndex(Lek_interface.Columns.LEK_DATA);
        int zazycieColumnId=cursor.getColumnIndex(Lek_interface.Columns.LEK_ZAZYCIE);//zazycie dopisane

        Lekarstwo lek = new Lekarstwo();
        lek.setId(cursor.getInt(idColumnId));
        lek.setNazwaLeku(cursor.getString(nazwaColumnId));
        lek.setIloscOpakowanie(cursor.getString(iloscColumnId));
        lek.setDataWaznosci(cursor.getString(dataColumnId));
        lek.setIsZazyte(cursor.getString(zazycieColumnId));
        //Log.d("epilbox", "mapCursorToLek: "+cursor.getString(zazycieColumnId));//zazycie dopisane
        return lek;
    }
}
