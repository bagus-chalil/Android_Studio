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

    private EditText editTextId;
    private EditText editTextKode,editTextMatakuliah,editTextSks,editTextAngka,editTextHuruf,editTextPredikat;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_obat);

        editTextId = findViewById(R.id.editTextId);
        editTextKode = findViewById(R.id.editTextKode);
        editTextMatakuliah = findViewById(R.id.editTextMatakuliah);
        editTextSks = findViewById(R.id.editTextSks);
        editTextAngka = findViewById(R.id.editTextAngka);
        editTextHuruf = findViewById(R.id.editTextHuruf);
        editTextPredikat = findViewById(R.id.editTextPredikat);

        buttonAdd = findViewById(R.id.buttonSimpan);
        buttonView = findViewById(R.id.buttonKembali);

        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }
    private void AddBarang(){
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
        if (grade == 'A' || grade =='B' || grade == 'C'){

            editTextPredikat.setText("Dinyatakan Lulus");
        }
        else {
            editTextPredikat.setText("Dinyatakan Tidak Lulus");
        }

        final String kode = editTextKode.getText().toString().trim();
        final String matakuliah = editTextMatakuliah.getText().toString().trim();
        final String sks = editTextSks.getText().toString().trim();
        final String angka = editTextAngka.getText().toString().trim();
        final String huruf = editTextHuruf.getText().toString().trim();
        final String predikat = editTextPredikat.getText().toString().trim();



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
                params.put(Konfigurasi.KEY_EMP_MATAKULIAH,matakuliah);
                params.put(Konfigurasi.KEY_EMP_SKS,sks);
                params.put(Konfigurasi.KEY_EMP_ANGKA,angka);
                params.put(Konfigurasi.KEY_EMP_HURUF,huruf);
                params.put(Konfigurasi.KEY_EMP_PREDIKAT,predikat);


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