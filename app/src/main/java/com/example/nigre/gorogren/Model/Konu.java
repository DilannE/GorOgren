package com.example.nigre.gorogren.Model;

/**
 * Created by Dell on 22.9.17.
 */

public class Konu {
    private String konuIsim;
    private String animasyon;


    public Konu(String konuIsim, String animasyon) {
        super();
        this.konuIsim = konuIsim;
        this.animasyon = animasyon;

    }


    public String getAnimasyon() {
        return animasyon;
    }

    public void setAnimasyon(String animasyon) {
        this.animasyon = animasyon;
    }

    public String getKonuIsim() {
        return konuIsim;
    }

    public void setKonuIsim(String konuIsim) {
        this.konuIsim = konuIsim;
    }


}

