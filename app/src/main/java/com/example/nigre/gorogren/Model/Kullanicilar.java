package com.example.nigre.gorogren.Model;

import android.widget.DatePicker;

/**
 * Created by nigre on 19.09.2017.
 */

public class Kullanicilar {

    private String email;
    private String sifre;
    private String Ad;
    private String Soyad;
    private boolean OgrenciMi;
    private String sinif;

    public Kullanicilar(String Ad,String Soyad,String email,String sifre,Boolean OgrenciMi){
        this.email=email;
        this.sifre=sifre;
        this.Ad=Ad;
        this.Soyad=Soyad;
        this.OgrenciMi=OgrenciMi;
    }

    public Kullanicilar(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getAd() {
        return Ad;
    }

    public void setAd(String ad) {
        Ad = ad;
    }

    public String getSoyad() {
        return Soyad;
    }

    public void setSoyad(String soyad) {
        Soyad = soyad;
    }


    public String getSinif() {
        return sinif;
    }

    public void setSinif(String sinif) {
        this.sinif = sinif;
    }

    public boolean isOgrenciMi() {
        return OgrenciMi;
    }

    public void setOgrenciMi(boolean ogrenciMi) {
        OgrenciMi = ogrenciMi;
    }
}
