package com.example.nigre.gorogren;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.nigre.gorogren.Model.Dersler;
import com.example.nigre.gorogren.NavDrawerFragments.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nigre on 21.08.2017.
 */

public class AnaSayfaFragment extends ListFragment {

    final List<Dersler> dersler=new ArrayList<Dersler>();
    ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();
    SimpleAdapter adapter;
    ListView listeDersler;


    String matKonular[] = new String [] {"Tanjant", "Sin-Cos"};
    String fizikKonular[] = new String [] {"Kuvvet", "İç Bükey Ayna", "Yakınsayan Merkez","Serap Olayı","Temel Odak","Çukur Odak","Çukur Resim","Derinlik","Doppler"};
    String biyolojiKonular[] = new String [] {"Hücre Zarından Madde Geçişi", "Fotosentez"};
    String kimyaKonular[] = new String [] {"Elektronlar"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        dersler.add(new Dersler("Matematik", R.drawable.matematik,matKonular));
        dersler.add(new Dersler("Fizik", R.drawable.fizik,fizikKonular));
        dersler.add(new Dersler("Biyoloji", R.drawable.biyoloji,biyolojiKonular));
        dersler.add(new Dersler("Kimya", R.drawable.kimya,kimyaKonular));

        HashMap<String,String> map = new HashMap<String,String>();

        for(Dersler object: dersler){

            map = new HashMap<String,String>();
            map.put("Ders",object.getIsim());
            map.put("DersResmi",Integer.toString(object.getImage()));

            data.add(map);

        }

        String[] from = {"Ders","DersResmi"};
        int[] to = {R.id.txtDersAdi,R.id.imgSimge};

        adapter = new SimpleAdapter(getActivity(),data,R.layout.fragment_anasayfa_satir,from,to);

        setListAdapter(adapter);
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String secilenDers= data.get(i).get("Ders");
                ArrayList secilenDersinKonulari = new ArrayList();

                for(Dersler object: dersler){
                    if(object.getIsim().equals(secilenDers)){
                        for(String s: object.getKonular()){

                            secilenDersinKonulari.add(s);
                        }
                    }
                }


                Intent intent = new Intent(getActivity(),KonuActivity.class);
                intent.putExtra("secilenders",secilenDersinKonulari);
                startActivity(intent);

            }
        });
    }
}
