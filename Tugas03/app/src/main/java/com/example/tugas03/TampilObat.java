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
    private EditText editTextMatakuliah,editTextAngka,editTextHuruf,editTextKode;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;
    private String kode,matakuliah,angka,huruf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_obat);

        Intent intent = getIntent();

        id = intent.getStringExtra(Konfigurasi.BRG_ID);
        kode = intent.getStringExtra(Konfigurasi.BRG_KODE);
        matakuliah = intent.getStringExtra(Konfigurasi.BRG_MATAKULIAH);
        angka = intent.getStringExtra(Konfigurasi.BRG_ANGKA);
        huruf = intent.getStringExtra(Konfigurasi.BRG_HURUF);

        editTextId = findViewById(R.id.editTextId);
        editTextKode = findViewById(R.id.editTextKode);
        editTextMatakuliah = findViewById(R.id.editTextMatakuliah);
        editTextAngka = findViewById(R.id.editTextAngka);
        editTextHuruf = findViewById(R.id.editTextHuruf);


        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextId.setText(id);
        editTextKode.setText(kode);
        editTextMatakuliah.setText(matakuliah);
        editTextAngka.setText(angka);
        editTextHuruf.setText(huruf);

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
            String matakuliah = c.getString(Konfigurasi.TAG_MATAKULIAH);
            String angka = c.getString(Konfigurasi.TAG_ANGKA);
            String huruf = c.getString(Konfigurasi.TAG_HURUF);
            editTextId.setText(id);
            editTextKode.setText(kode);
            editTextMatakuliah.setText(matakuliah);
            editTextAngka.setText(angka);
            editTextHuruf.setText(huruf);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateBarang(){
        final Double a = Double.parseDouble(String.valueOf(editTextAngka.getText()));
        char grade=' ';
        if (a >= 85.00){
            grade = 'A';
            editTextHuruf.setText(""+grade);
        }
        else if (a >= 80.00 && a <=89.00 ){
            grade ='B';
            editTextHuruf.setText(""+grade);
        }
        else if (a >= 70.00 && a <= 79.00){
            grade = 'C';
            editTextHuruf.setText(""+grade);
        }
        else if (a >= 40.00 && a <= 69.00){
            grade = 'D';
            editTextHuruf.setText(""+grade);
        }
        else if (a <= 39.00){
            grade = 'E';
            editTextHuruf.setText(""+grade);
        }

        final String id = editTextId.getText().toString().trim();
        final String kode = editTextKode.getText().toString().trim();
        final String matakuliah = editTextMatakuliah.getText().toString().trim();
        final String angka = editTextAngka.getText().toString().trim();
        final String huruf = editTextHuruf.getText().toString().trim();


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
                hashMap.put(Konfigurasi.KEY_EMP_MATAKULIAH,matakuliah);
                hashMap.put(Konfigurasi.KEY_EMP_ANGKA,angka);
                hashMap.put(Konfigurasi.KEY_EMP_HURUF,huruf);

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
