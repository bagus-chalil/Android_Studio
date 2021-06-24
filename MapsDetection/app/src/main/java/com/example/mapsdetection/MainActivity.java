package com.example.mapsdetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText tAwal,tAkhir;
    Button tblCari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tAwal=findViewById(R.id.edit_awal);
        tAkhir=findViewById(R.id.edit_akhir);

        tblCari=findViewById(R.id.btn_cari);

        tblCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dtAwal=tAwal.getText().toString().trim();
                String dtAkhir=tAkhir.getText().toString().trim();

                if (dtAwal.equals("") && dtAkhir.equals("")){
                    Toast.makeText(MainActivity.this,"Data Tidak Boleh Kosong",Toast.LENGTH_LONG).show();
                }else{
                    TampilkanPeta(dtAwal,dtAkhir);{
                    }
                }
            }
        });
    }
    private void TampilkanPeta(String dtAwal, String dtAkhir) {
        try {
            Uri uri=Uri.parse("https://www.google.co.id/maps/search/"+dtAwal+"+"+dtAkhir);
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags((Intent.FLAG_ACTIVITY_NEW_TASK));
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Uri uri=Uri.parse("https://www.play.google.com/store/apps/details?=id=com.google.android.apps.maps");
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags((Intent.FLAG_ACTIVITY_NEW_TASK));
            startActivity(intent);
        }
    }
}