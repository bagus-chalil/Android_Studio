package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    InterfaceBarang interfaceBarang;
    AdapterBarang adapter;
    Button tbladd,tblexit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.listData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tbladd=findViewById(R.id.buttonadd);
        tblexit=findViewById(R.id.buttonexit);

        interfaceBarang=APICLient.getClient().create (InterfaceBarang.class);
        tampilkanData();
    }

    private void tampilkanData() {
        Call<List<Barang>> getBarang=interfaceBarang.getBarang();
        getBarang.enqueue(new Callback<List<Barang>>() {
            private Object ArrayList;

            @Override
            public void onResponse(Call<List<Barang>> call, Response<List<Barang>> response) {
                java.util.ArrayList<Barang> listbarang=(ArrayList<Barang>) response.body();
                adapter= new AdapterBarang(listbarang);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Barang>> call, Throwable t) {

            }
        });
    }

    public void tambah_data(View view) {
        Intent intent=new Intent(MainActivity.this,TambahBarang.class);
        startActivity(intent);
    }
}
