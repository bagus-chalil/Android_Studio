package com.example.tugas03;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
public class TambahObat extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextKode,editTextNama,editTextSatuan,editTextJumlah_Obat,editTextHarga,editTextKadaluwarsa;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_obat);

        editTextKode = findViewById(R.id.editTextKode);
        editTextNama = findViewById(R.id.editTextNama);
        editTextSatuan = findViewById(R.id.editTextSatuan);
        editTextJumlah_Obat = findViewById(R.id.editTextJumlah);
        editTextHarga = findViewById(R.id.editTextharga);
        editTextKadaluwarsa = findViewById(R.id.editTextkadaluwarsa);

        buttonAdd = findViewById(R.id.buttonSimpan);
        buttonView = findViewById(R.id.buttonKembali);

        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }
    private void AddBarang(){

        final String kode = editTextKode.getText().toString().trim();
        final String nama = editTextNama.getText().toString().trim();
        final String satuan = editTextSatuan.getText().toString().trim();
        final String jumlah_obat = editTextJumlah_Obat.getText().toString().trim();
        final String harga = editTextHarga.getText().toString().trim();
        final String kadaluwarsa = editTextKadaluwarsa.getText().toString().trim();



        class AddBarang extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahObat.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TambahObat.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_EMP_KODE,kode);
                params.put(Konfigurasi.KEY_EMP_NAMA,nama);
                params.put(Konfigurasi.KEY_EMP_SATUAN,satuan);
                params.put(Konfigurasi.KEY_EMP_HARGA,jumlah_obat);
                params.put(Konfigurasi.KEY_EMP_JUMLAH_OBAT,harga);
                params.put(Konfigurasi.KEY_EMP_KADALUWARSA,kadaluwarsa);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.URL_ADD, params);
                return res;
            }
        }

        AddBarang ab = new AddBarang();
        ab.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            AddBarang();
        }

        if(v == buttonView){
            startActivity(new Intent(this, TampilSemua_Obat.class));
        }
    }

    public void huruf(View view) {
    }
}