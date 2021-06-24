package com.example.tugas03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TampilSemua_Obat extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_semua_obat);
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }


    private void showItem(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Konfigurasi.TAG_ID);
                String kode = jo.getString(Konfigurasi.TAG_KODE);
                String matakuliah = jo.getString(Konfigurasi.TAG_MATAKULIAH);
                String sks = jo.getString(Konfigurasi.TAG_SKS);
                String angka = jo.getString(Konfigurasi.TAG_ANGKA);
                String huruf = jo.getString(Konfigurasi.TAG_HURUF);
                String predikat = jo.getString(Konfigurasi.TAG_PREDIKAT);

                HashMap<String,String> item = new HashMap<>();
                item.put(Konfigurasi.TAG_ID,id);
                item.put(Konfigurasi.TAG_KODE,kode);
                item.put(Konfigurasi.TAG_MATAKULIAH,matakuliah);
                item.put(Konfigurasi.TAG_SKS,sks);
                item.put(Konfigurasi.TAG_ANGKA,angka);
                item.put(Konfigurasi.TAG_HURUF,huruf);
                item.put(Konfigurasi.TAG_PREDIKAT,predikat);
                list.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                TampilSemua_Obat.this, list, R.layout.list_item,
                new String[]{Konfigurasi.TAG_ID,Konfigurasi.TAG_MATAKULIAH,Konfigurasi.TAG_SKS
                        ,Konfigurasi.TAG_ANGKA,Konfigurasi.TAG_HURUF,Konfigurasi.TAG_PREDIKAT},
                new int[]{R.id.no, R.id.matkul, R.id.jml_sks, R.id.n_angka, R.id.n_huruf});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilSemua_Obat.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showItem();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Konfigurasi.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, TampilObat.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String brgId = map.get(Konfigurasi.TAG_ID).toString();
        String brgKode = map.get(Konfigurasi.TAG_KODE).toString();
        String brgMatakuliah = map.get(Konfigurasi.TAG_MATAKULIAH).toString();
        String brgSks = map.get(Konfigurasi.TAG_SKS).toString();
        String brgAngka = map.get(Konfigurasi.TAG_ANGKA).toString();
        String brgHuruf = map.get(Konfigurasi.TAG_HURUF).toString();
        String brgPredikat = map.get(Konfigurasi.TAG_PREDIKAT).toString();
        intent.putExtra(Konfigurasi.BRG_ID,brgId);
        intent.putExtra(Konfigurasi.BRG_KODE,brgKode);
        intent.putExtra(Konfigurasi.BRG_MATAKULIAH,brgMatakuliah);
        intent.putExtra(Konfigurasi.BRG_SKS,brgSks);
        intent.putExtra(Konfigurasi.BRG_ANGKA,brgAngka);
        intent.putExtra(Konfigurasi.BRG_HURUF,brgHuruf);
        intent.putExtra(Konfigurasi.BRG_PREDIKAT,brgPredikat);
        startActivity(intent);
    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
}