package com.example.latihan5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditDataBarang extends AppCompatActivity {
    EditText edtkode,edtnama,edtharga;
    String nkode,nnama,nharga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_barang);

        Intent intent=getIntent();

        edtkode=findViewById(R.id.ekode);
        edtnama=findViewById(R.id.enama);
        edtharga=findViewById(R.id.eharga);

        nkode= intent.getStringExtra("kode");
        nnama= intent.getStringExtra("nama");
        nharga= intent.getStringExtra("harga");
        edtkode.setText(nkode);
        edtnama.setText(nnama);
        edtharga.setText(nharga);
    }

    public void UpdateBarang(View view) {
        String xkode=edtkode.getText().toString();
        String xnama=edtnama.getText().toString();
        String xharga=edtharga.getText().toString();

        String method="Update Data Barang";
        Bantu_Update bu=new Bantu_Update(this);
        bu.execute(method,xkode,xnama,xharga);
        finish();

    }

    public void Lihat(View view) {
        Intent edit=new Intent(EditDataBarang.this,MainActivity.class);
        startActivity(edit);
    }
}