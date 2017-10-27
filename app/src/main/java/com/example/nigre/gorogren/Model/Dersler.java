package com.example.nigre.gorogren.Model;

/**
 * Created by Dell on 22.9.17.
 */


public class Dersler {

    private String  isim;
    private int resim;
    private String[] konular;


    public String[] getKonular() {
        return konular;
    }

    public void setKonular(String[] konular) {
        this.konular = konular;
    }


    public Dersler(String isim, int resim,String[] konular) {
        super();
        this.isim = isim;
        this.resim = resim;
        this.konular = konular;
    }

    @Override
    public String toString() {
        return isim;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public int getImage() {
        return resim;
    }

    public void setImage(int resim) {
        this.resim  = resim;
    }
}
