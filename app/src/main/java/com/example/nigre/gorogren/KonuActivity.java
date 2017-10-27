package com.example.nigre.gorogren;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class KonuActivity extends AppCompatActivity {


    ListView listeKonu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konu);
        Bundle extras = getIntent().getExtras();
        ArrayList value = extras.getStringArrayList("secilenders");


        listeKonu=(ListView) findViewById(R.id.listKonular);

        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, value);

        //(C) adımı
        listeKonu.setAdapter(veriAdaptoru);

        listeKonu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String secilenKonu = (String) adapterView.getItemAtPosition(i).toString();

                Intent intent = new Intent(getApplicationContext(),AnimasyonActivity.class);
                intent.putExtra("secilenkonu",secilenKonu);
                startActivity(intent);
            }
        });

    }
}
