package com.example.nigre.gorogren.NavDrawerFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.nigre.gorogren.R;


/**
 * Created by nigre on 21.08.2017.
 */

public class ProfilFragment extends Fragment {
    TextView mTxtKullaniciAdi;
    FloatingActionButton mFltButtonFotoSec;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profil,container,false);
        mTxtKullaniciAdi=(TextView)v.findViewById(R.id.txt_ad_soyad);
        mFltButtonFotoSec=(FloatingActionButton)v.findViewById(R.id.fab);



        return v;


    }

    }

