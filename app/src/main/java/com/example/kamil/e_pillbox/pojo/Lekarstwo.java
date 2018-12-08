package com.example.kamil.e_pillbox.pojo;

import android.content.Intent;

import java.io.Serializable;
@SuppressWarnings("serial")
public class Lekarstwo implements Serializable {
    private Integer id;
    private String nazwaLeku;
    private String iloscOpakowanie;
    private String dataWaznosci;
    private String isZazyte;



    public String getIsZazyte() {
        return isZazyte;
    }

    public void setIsZazyte(String isZazyte) {
        this.isZazyte = isZazyte;
    }

    //dopisane aby dodac isZazyte
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwaLeku(){
        return nazwaLeku;
    }

    public void setNazwaLeku(String nazwaLeku) {
        this.nazwaLeku = nazwaLeku;
    }

    public String getIloscOpakowanie() {
        return iloscOpakowanie;
    }

    public void setIloscOpakowanie(String iloscOpakowanie) {
        this.iloscOpakowanie = iloscOpakowanie;
    }

    public String getDataWaznosci() {
        return dataWaznosci;
    }

    public void setDataWaznosci(String dataWaznosci) {
        this.dataWaznosci = dataWaznosci;
    }


    @Override
    public String toString() {
        return super.toString();
    }


}
