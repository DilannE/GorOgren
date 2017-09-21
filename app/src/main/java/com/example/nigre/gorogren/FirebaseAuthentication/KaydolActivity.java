package com.example.nigre.gorogren.FirebaseAuthentication;

import android.content.Intent;
import android.media.Image;
import android.net.PskKeyManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nigre.gorogren.Model.Kullanicilar;
import com.example.nigre.gorogren.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nigre on 15.09.2017.
 */

public class KaydolActivity extends AppCompatActivity {
    Button mBtnKaydol;
    EditText mEtEmail_kaydol;
    EditText mEtParola_kaydol;
    EditText mETAd;
    EditText mETSoyad;
    CheckBox mCbOgrenciMisin;
    Spinner mSpSinif;
    String Email;
    String Parola;
    String Ad;
    String Soyad;
    Boolean OgrenciMi;
    ImageView mImgKullaniciFotografi;
    private DatabaseReference databaseReferenceCustomers;
    private FirebaseAuth auth;
    FirebaseDatabase db;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaydol);

        mEtEmail_kaydol=(EditText)findViewById(R.id.et_kullanici_adi_kaydol);
        mEtParola_kaydol=(EditText)findViewById(R.id.et_kullanici_parola_kaydol);
        mETAd=(EditText)findViewById(R.id.et_ad);
        mETSoyad=(EditText)findViewById(R.id.et_soyad);
        mCbOgrenciMisin=(CheckBox)findViewById(R.id.cb_ogrenciMisin);
        mSpSinif=(Spinner)findViewById(R.id.spn_siniflar);
        mBtnKaydol=(Button)findViewById(R.id.btn_kayit_kaydol);
        mImgKullaniciFotografi=(ImageView)findViewById(R.id.imageView_kullanici_fotografi);
        db=FirebaseDatabase.getInstance();


        mBtnKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Email = mEtEmail_kaydol.getText().toString();
                Parola = mEtParola_kaydol.getText().toString();
                Kullanicilar kullanicilar = new Kullanicilar();
                kullanicilar.setAd(mETAd.getText().toString());
                Ad = kullanicilar.getAd();
                kullanicilar.setSoyad(mETSoyad.getText().toString());
                Soyad = kullanicilar.getSoyad();
                kullanicilar.setOgrenciMi(mCbOgrenciMisin.isChecked());
                OgrenciMi=kullanicilar.isOgrenciMi();



                auth = FirebaseAuth.getInstance();

                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(getApplicationContext(), "Lütfen emailinizi giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Parola)) {
                    Toast.makeText(getApplicationContext(), "Lütfen parolanızı giriniz", Toast.LENGTH_SHORT).show();
                }
                if (Parola.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Parola en az 6 haneli olmalıdır", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(Ad)) {
                    Toast.makeText(getApplicationContext(), "Lütfen Adınızı giriniz", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(Soyad)) {
                    Toast.makeText(getApplicationContext(), "Lütfen Soyadınızı giriniz", Toast.LENGTH_SHORT).show();
                }

                if(Ad.isEmpty()|| Soyad.isEmpty() || Email.isEmpty() || Parola.isEmpty()){
                    Toast.makeText(KaydolActivity.this, "Lütfen İlgili Alanları Doldurduğunuzdan Emin Olun..", Toast.LENGTH_SHORT).show();

                }
                else{
                    Kaydol();
                    FirebaseDatabaseKaydet(Ad,Soyad,Email,Parola,OgrenciMi);
                }


            }

        });

    }
    public void Kaydol(){
        auth.createUserWithEmailAndPassword(Email, Parola).addOnCompleteListener(KaydolActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(KaydolActivity.this, "Yetkilendirme Hatası",
                            Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(KaydolActivity.this, GirisActivity.class));
                    finish();


                }
            }
        });
    }

    public void FirebaseDatabaseKaydet(String Adi,String Soyadi, String Emaili,String Parolasi,Boolean OgrenciDurumu){
        DatabaseReference dbRef= db.getReference("KullaniciVerileri");
        String key= dbRef.push().getKey();
        DatabaseReference dbRefYeni=db.getReference("KullaniciVerileri/"+key);
        dbRefYeni.setValue(new Kullanicilar(Ad,Soyad,Email,Parola,OgrenciMi));

    }

    }



    //Sınıflar kısmına spinnerda veriler eklenmeli ve bunlar database e yazılımalı.


