package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.ResourceBundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.FormUrlEncoded;

public class UbahHapusData extends AppCompatActivity {
    EditText editkode,editnama,editharga;
    InterfaceBarang interfaceBarang;
    Barang barang;
    Button tblDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_hapus_data);

        editkode=findViewById(R.id.ekode);
        editnama=findViewById(R.id.enmbrg);
        editharga=findViewById(R.id.eharga);
        tblDelete=findViewById(R.id.buttonhapus);
        Bundle bundle = getIntent().getExtras();

        editkode.setText(bundle.getString("kode"));
        editnama.setText(bundle.getString("nama"));
        editharga.setText(bundle.getString("harga"));

    }

    public void hapusdata(View view) {
        interfaceBarang=APICLient.getClient().create(InterfaceBarang.class);

        Call<Barang> delBarang=interfaceBarang.deleteBarang(editkode.getText().toString());
        delBarang.enqueue(new Callback<Barang>() {
            @Override
            public void onResponse(Call<Barang> call, Response<Barang> response) {
                Log.d("delete",response.toString());
                Toast.makeText(UbahHapusData.this, "Hapus data Sukses !", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Barang> call, Throwable t) {
                Log.d("error_barang",t.getMessage());
            }
        });
        finish();
    }

    public void update_data(View view) {

    }
}
