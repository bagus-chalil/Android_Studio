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
                String nama = jo.getString(Konfigurasi.TAG_NAMA);
                String satuan  = jo.getString(Konfigurasi.TAG_SATUAN);
                String jumlah_obat = jo.getString(Konfigurasi.TAG_JUMLAH_OBAT);
                String harga = jo.getString(Konfigurasi.TAG_HARGA);
                String kadaluwarsa = jo.getString(Konfigurasi.TAG_KADALUWARSA);

                HashMap<String,String> item = new HashMap<>();
                item.put(Konfigurasi.TAG_ID,id);
                item.put(Konfigurasi.TAG_KODE,kode);
                item.put(Konfigurasi.TAG_NAMA,nama);
                item.put(Konfigurasi.TAG_SATUAN,satuan);
                item.put(Konfigurasi.TAG_JUMLAH_OBAT,jumlah_obat);
                item.put(Konfigurasi.TAG_HARGA,harga);
                item.put(Konfigurasi.TAG_KADALUWARSA,kadaluwarsa);
                list.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                TampilSemua_Obat.this, list, R.layout.list_item,
                new String[]{Konfigurasi.TAG_ID,Konfigurasi.TAG_KODE,Konfigurasi.TAG_NAMA,Konfigurasi.TAG_SATUAN
                        ,Konfigurasi.TAG_JUMLAH_OBAT,Konfigurasi.TAG_HARGA,Konfigurasi.TAG_KADALUWARSA},
                new int[]{R.id.no, R.id.kode, R.id.nama, R.id.satuan, R.id.jumlah_obat,R.id.harga, R.id.kadaluwarsa});

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
        String Id = map.get(Konfigurasi.TAG_ID).toString();
        String obt_kode = map.get(Konfigurasi.TAG_KODE).toString();
        String obt_nama = map.get(Konfigurasi.TAG_NAMA).toString();
        String obt_satuan = map.get(Konfigurasi.TAG_SATUAN).toString();
        String obt_jumlah_obat = map.get(Konfigurasi.TAG_JUMLAH_OBAT).toString();
        String obt_harga = map.get(Konfigurasi.TAG_HARGA).toString();
        String obt_kadaluwarsa = map.get(Konfigurasi.TAG_KADALUWARSA).toString();
        intent.putExtra(Konfigurasi.BRG_ID,Id);
        intent.putExtra(Konfigurasi.BRG_KODE,obt_kode);
        intent.putExtra(Konfigurasi.TAG_NAMA,obt_nama);
        intent.putExtra(Konfigurasi.TAG_SATUAN,obt_satuan);
        intent.putExtra(Konfigurasi.TAG_JUMLAH_OBAT,obt_jumlah_obat);
        intent.putExtra(Konfigurasi.TAG_HARGA,obt_harga);
        intent.putExtra(Konfigurasi.TAG_KADALUWARSA,obt_kadaluwarsa);
        startActivity(intent);
    }
}