package com.example.latihan5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Entry_Data_Barang extends AppCompatActivity {
    EditText ckode,cnama,charga;
    String mkode,mnama,mharga;

    Button btnsave,btnview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_data_barang);
        ckode=findViewById(R.id.xkode);
        cnama=findViewById(R.id.xnama);
        charga=findViewById(R.id.xharga);

        btnsave=findViewById(R.id.btnSimpan);
        btnview=findViewById(R.id.btnView);
    }

    public void saveBarang(View view) {
        mkode=ckode.getText().toString();
        mnama=cnama.getText().toString();
        mharga=charga.getText().toString();

        String method="Entry Data Barang";
        BantuSimpan bs=new BantuSimpan(this);
        bs.execute(method,mkode,mnama,mharga);
        finish();
    }

    public void viewBarang(View view) {
        Intent edit=new Intent(Entry_Data_Barang.this,MainActivity.class);
        startActivity(edit);
    }
}