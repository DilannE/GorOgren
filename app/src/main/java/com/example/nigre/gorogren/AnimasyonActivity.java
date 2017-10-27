package com.example.nigre.gorogren;

import com.example.nigre.gorogren.Model.Konu;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.felipecsl.gifimageview.library.GifImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AnimasyonActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txt;

    final List<Konu> mKonular=new ArrayList<Konu>();
    Button btnStart,btnStop;
    GifImageView mGifImageView;
    String animasyon ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animasyon);

        Bundle extras = getIntent().getExtras();
        String value = extras.getString("secilenkonu");

        mKonular.add(new Konu("Tanjant","https://media.giphy.com/media/l1J9rB1ZRcV9ORpVS/giphy.gif"));
        mKonular.add(new Konu("Faktöriyel", "http://4.bp.blogspot.com/-Tg1yvXfJQ38/TwhaYVNVz8I/AAAAAAAANCk/HYp4AntzdTY/s1600/hareketli_tweety_gifleri_109.gif"));
        mKonular.add(new Konu("Limit", "http://www.egitimevi.net/hareketli-resim/hayvan/anima047.gif"));
        mKonular.add(new Konu("Türev", "https://media.giphy.com/media/3o6ZtmDAQdrDfaTWEw/giphy.gif"));
        mKonular.add(new Konu("Kuvvet", "https://media.giphy.com/media/xT9Igw15STuUMVaka4/giphy.gif"));
        mKonular.add(new Konu("İç Bükey Ayna", "https://media.giphy.com/media/l378bulfGeS6wctSo/giphy.gif"));
        mKonular.add(new Konu("Yakınsayan Merkez", "https://media.giphy.com/media/3o7aDaUQfvIH5R7s9G/giphy.gif"));
        mKonular.add(new Konu("Temel Odak", "https://media.giphy.com/media/l378oI3iy5oa5TILm/giphy.gif"));
        mKonular.add(new Konu("Çukur Odak", "https://media.giphy.com/media/xT9IgjZCMoN1sIfAwE/giphy.gif"));
        mKonular.add(new Konu("Çukur Resim", "https://media.giphy.com/media/xT9IgvyNHPZufpHEVW/giphy.gif"));
        mKonular.add(new Konu("Derinlik", "https://media.giphy.com/media/3ohhwyhyXEr6V6KNig/giphy.gif"));
        mKonular.add(new Konu("Serap Olayı","https://gifyu.com/images/serapolayi.md.gif"));
        mKonular.add(new Konu("Doppler","https://gifyu.com/images/DOPPLER.gif"));
        mKonular.add(new Konu("Hücre Zarından Madde Geçişi","https://media.giphy.com/media/ETXEWMy4ckOJO/giphy.gif"));
        mKonular.add(new Konu("Fotosentez","https://media.giphy.com/media/YNprPNLA5mb6/giphy.gif"));
        mKonular.add(new Konu("Elektronlar","http://1.bp.blogspot.com/-byR95WHF8Ks/T_rEVJyYF0I/AAAAAAAAAJQ/yN6km3Qj-iI/s1600/Atomo.gif"));
        mKonular.add(new Konu("Sin-Cos","http://static2.businessinsider.com/image/51910f38ecad040506000002/uppkwr9%20-%20imgur.gif"));




        for(Konu object: mKonular){
            if(object.getKonuIsim().equals(value)){
                animasyon = object.getAnimasyon();
            }
        }

       /* txt = (TextView)findViewById(R.id.txtAnimasyonAdi);
        txt.setText(value);*/

        mGifImageView = (GifImageView)findViewById(R.id.giftImage);
        btnStart = (Button)findViewById(R.id.btnBasla);
        btnStop = (Button)findViewById(R.id.btnStop);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id==R.id.btnBasla){
            new RetrieveByteArray().execute(animasyon);
            mGifImageView.startAnimation();
        }else if(id == R.id.btnStop){
            mGifImageView.stopAnimation();
        }
    }

    class RetrieveByteArray extends AsyncTask<String,Void,byte[]>{

        @Override
        protected byte[] doInBackground(String... strings) {
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if(urlConnection.getResponseCode()==200){
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                    int nRead;
                    byte[] data = new byte[10240];
                    while ((nRead = in.read(data,0,data.length)) != -1){
                        buffer.write(data,0,nRead);
                    }

                    buffer.flush();;
                    return  buffer.toByteArray();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return  null;
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            super.onPostExecute(bytes);
            mGifImageView.setBytes(bytes);
        }
    }
}