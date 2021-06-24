package com.example.tugas04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button tblBesarkan,tblrotasikan;
    ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gambar = findViewById(R.id.logoandroid);
        tblBesarkan = findViewById(R.id.besarkan);
        tblrotasikan = findViewById(R.id.rotasikan);

        tblBesarkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tambahbesar);
                //ImageView image1 = (ImageView)findViewById(R.id.logoandroid);
                ImageView image1 = gambar;
                image1.startAnimation(animation);
            }
        });
        tblrotasikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotasi);
                ImageView image1 = gambar;
                image1.startAnimation(animation);
            }
        });
        
    }
}