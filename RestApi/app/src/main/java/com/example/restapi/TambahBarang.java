package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahBarang extends AppCompatActivity {
    EditText xkd_brg, xnm_brg, xhrg_brg;
    InterfaceBarang interfaceBarang;
    Button btnsimpan, btlview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_barang);

        xkd_brg = findViewById(R.id.xkode);
        xnm_brg = findViewById(R.id.xnmbrg);
        xhrg_brg = findViewById(R.id.xharga);
        btnsimpan = findViewById(R.id.buttonadd);
        interfaceBarang = APICLient.getClient().create(InterfaceBarang.class);
    }

    public void simpandata(View view) {
        String kode_brg = xkd_brg.getText().toString();
        String nama_brg = xnm_brg.getText().toString();
        String harga_brg = xhrg_brg.getText().toString();

        Call<Barang> postBarang = interfaceBarang.postBarang(kode_brg, nama_brg, harga_brg);
        postBarang.enqueue(new Callback<Barang>() {
            @Override
            public void onResponse(Call<Barang> call, Response<Barang> response) {
                Toast.makeText(TambahBarang.this, "Save data Success", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(TambahBarang.this,MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Barang> call, Throwable t) {
                Toast.makeText(TambahBarang.this, "Save data not Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
