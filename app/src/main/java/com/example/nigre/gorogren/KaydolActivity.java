package com.example.nigre.gorogren;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by nigre on 15.09.2017.
 */

public class KaydolActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    Button mBtnKaydol;
    EditText mETKullaniciAdi_kaydol;
    EditText mETParola_kaydol;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaydol);

        mETKullaniciAdi_kaydol=(EditText)findViewById(R.id.et_kullanici_adi_kaydol);
        mETParola_kaydol=(EditText)findViewById(R.id.et_kullanici_parola_kaydol);

        mBtnKaydol=(Button)findViewById(R.id.btn_kayit_kaydol);
        mBtnKaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String KullaniciAdi;
                String Parola;

                KullaniciAdi=mETKullaniciAdi_kaydol.getText().toString();
                Parola=mETParola_kaydol.getText().toString();
                auth=FirebaseAuth.getInstance();

                if(TextUtils.isEmpty(KullaniciAdi)){
                    Toast.makeText(getApplicationContext(),"Lütfen emailinizi giriniz", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Parola)){
                    Toast.makeText(getApplicationContext(),"Lütfen parolanızı giriniz",Toast.LENGTH_SHORT).show();
                }
                if (Parola.length()<6){
                    Toast.makeText(getApplicationContext(),"Parola en az 6 haneli olmalıdır",Toast.LENGTH_SHORT).show();
                }


                auth.createUserWithEmailAndPassword(KullaniciAdi,Parola).addOnCompleteListener(KaydolActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(KaydolActivity.this, "Yetkilendirme Hatası",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            startActivity(new Intent(KaydolActivity.this, GirisActivity.class));
                            finish();


                        }
                    }
                });
            }
        });




    }
}
