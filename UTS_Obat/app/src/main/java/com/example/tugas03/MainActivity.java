package com.example.tugas03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void entry(View view) {
        Intent plus=new Intent(MainActivity.this, TambahObat.class);
        startActivity(plus);
    }

    public void View(View view) {
        Intent lihat=new Intent(MainActivity.this, TampilSemua_Obat.class);
        startActivity(lihat);
    }
    public void Tentang(View view) {
        Intent tentang=new Intent(MainActivity.this, TampilSemua_Obat.class);
        startActivity(tentang);
    }
    public void Exit(View view) {
        Intent exit=new Intent(MainActivity.this, TampilSemua_Obat.class);
        startActivity(exit);
    }
}