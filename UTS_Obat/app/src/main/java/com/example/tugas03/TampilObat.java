package com.example.tugas03;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TampilObat extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextId;
    private EditText editTextKode,editTextNama,editTextSatuan,editTextJumlah,editTextHarga,editTextKadaluwarsa;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;
    private String kode,nama,satuan,jumlah_obat,harga,kadaluwarsa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_obat);

        Intent intent = getIntent();

        id = intent.getStringExtra(Konfigurasi.BRG_ID);
        kode = intent.getStringExtra(Konfigurasi.BRG_KODE);
        nama = intent.getStringExtra(Konfigurasi.BRG_NAMA);
        satuan = intent.getStringExtra(Konfigurasi.BRG_SATUAN);
        jumlah_obat = intent.getStringExtra(Konfigurasi.BRG_JUMLAH_OBAT);
        harga = intent.getStringExtra(Konfigurasi.BRG_HARGA);
        kadaluwarsa = intent.getStringExtra(Konfigurasi.BRG_KADALUWARSA);

        editTextId = findViewById(R.id.editTextId);
        editTextKode = findViewById(R.id.editTextKode);
        editTextNama = findViewById(R.id.editTextNama);
        editTextSatuan = findViewById(R.id.editTextSatuan);
        editTextJumlah = findViewById(R.id.editTextjumlah);
        editTextHarga = findViewById(R.id.editTextHarga);
        editTextKadaluwarsa = findViewById(R.id.editTextKadaluwarsa);


        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextId.setText(id);
        editTextKode.setText(kode);
        editTextNama.setText(nama);
        editTextSatuan.setText(satuan);
        editTextJumlah.setText(jumlah_obat);
        editTextHarga.setText(harga);
        editTextKadaluwarsa.setText(kadaluwarsa);

        getBarang();
    }

    private void getBarang(){
        class GetBarang extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilObat.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showBarang(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_GET_BRG,id);
                return s;
            }
        }
        GetBarang gb = new GetBarang();
        gb.execute();
    }

    private void showBarang(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String id = c.getString(Konfigurasi.TAG_ID);
            String kode = c.getString(Konfigurasi.TAG_KODE);
            String nama = c.getString(Konfigurasi.TAG_NAMA);
            String satuan = c.getString(Konfigurasi.TAG_SATUAN);
            String jumlah_obat = c.getString(Konfigurasi.TAG_JUMLAH_OBAT);
            String harga = c.getString(Konfigurasi.TAG_HARGA);
            String kadaluwarsa = c.getString(Konfigurasi.TAG_KADALUWARSA);

            editTextId.setText(id);
            editTextKode.setText(kode);
            editTextNama.setText(nama);
            editTextSatuan.setText(satuan);
            editTextJumlah.setText(jumlah_obat);
            editTextHarga.setText(harga);
            editTextKadaluwarsa.setText(kadaluwarsa);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateBarang(){

        final String id = editTextId.getText().toString().trim();
        final String kode = editTextKode.getText().toString().trim();
        final String nama = editTextNama.getText().toString().trim();
        final String satuan = editTextSatuan.getText().toString().trim();
        final String jumlah_obat = editTextJumlah.getText().toString().trim();
        final String harga = editTextHarga.getText().toString().trim();
        final String kadaluwarsa = editTextKadaluwarsa.getText().toString().trim();


        class UpdateBarang extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilObat.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilObat.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_EMP_ID,id);
                hashMap.put(Konfigurasi.KEY_EMP_KODE,kode);
                hashMap.put(Konfigurasi.KEY_EMP_NAMA,nama);
                hashMap.put(Konfigurasi.KEY_EMP_SATUAN,satuan);
                hashMap.put(Konfigurasi.KEY_EMP_JUMLAH_OBAT,jumlah_obat);
                hashMap.put(Konfigurasi.KEY_EMP_HARGA,harga);
                hashMap.put(Konfigurasi.KEY_EMP_KADALUWARSA,kadaluwarsa);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Konfigurasi.URL_UPDATE_BRG,hashMap);

                return s;
            }
        }

        UpdateBarang ub = new UpdateBarang();
        ub.execute();
    }

    private void deleteBarang(){
        class DeleteBarang extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilObat.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilObat.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_DELETE_BRG, id);
                return s;
            }
        }

        DeleteBarang db = new DeleteBarang();
        db.execute();
    }

    private void confirmDeleteBarang(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Barang ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteBarang();
                        startActivity(new Intent(TampilObat.this, TampilSemua_Obat.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateBarang();
        }

        if(v == buttonDelete){
            confirmDeleteBarang();
        }
    }
}
