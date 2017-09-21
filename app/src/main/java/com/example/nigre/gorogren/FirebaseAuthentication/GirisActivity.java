package com.example.nigre.gorogren.FirebaseAuthentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nigre.gorogren.MainActivity;
import com.example.nigre.gorogren.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by nigre on 15.09.2017.
 */

public class GirisActivity extends AppCompatActivity {

    EditText mETKullaniciAdi_giris;
    EditText mETParola_giris;
    Button mBtnGiris;
    Button mBtnKaydol;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    String KullaniciAdi;
    String Parola;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giris);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();


        mETKullaniciAdi_giris = (EditText) findViewById(R.id.et_kullanici_adi_giris);
        mETParola_giris = (EditText) findViewById(R.id.et_kullanici_parola_giris);
        mBtnGiris = (Button) findViewById(R.id.btn_giris);
        mBtnKaydol = (Button) findViewById(R.id.btn_kaydol_girisPage);



        mBtnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                KullaniciAdi = mETKullaniciAdi_giris.getText().toString();
                Parola = mETParola_giris.getText().toString();

                if (KullaniciAdi==null || Parola==null) {
                    Toast.makeText(getApplicationContext(), "Lütfen gerekli alanları doldurunuz!", Toast.LENGTH_SHORT).show();
                } else {

                    mAuth.signInWithEmailAndPassword(KullaniciAdi, Parola).addOnCompleteListener(GirisActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent i = new Intent(GirisActivity.this, MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        // hata
                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });


        mBtnKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GirisActivity.this, KaydolActivity.class);
                startActivity(intent);
            }
        });


    }
}



