package com.example.latihan5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener{
    ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listdata);
        listView.setOnItemClickListener(this);
        getJSON();
    }


    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray("result");

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String kode = jo.getString("kode");
                String nama = jo.getString("nama");
                String harga = jo.getString("harga");

                HashMap<String,String> barang = new HashMap<>();
                barang.put("kode",kode);
                barang.put("nama",nama);
                barang.put("harga",harga);
                list.add(barang);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                MainActivity.this, list, R.layout.list_format,
                new String[]{"kode","nama","harga"},
                new int[]{R.id.id, R.id.nama, R.id.harga});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<String,Void,String>{

            private static final String URL_GET_ALL = "http://192.168.100.73:8085/android/latihan4/tampil_data.php";
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(String... params) {
                StringBuilder sb =  new StringBuilder();
                try {
                    URL url = new URL(URL_GET_ALL);
                    HttpURLConnection koneksi = (HttpURLConnection) url.openConnection();

                    BufferedReader br = new BufferedReader(new InputStreamReader(koneksi.getInputStream()));
                    String json;
                    while ((json = br.readLine()) !=null){
                        sb.append(json+"\n");
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,""+e,Toast.LENGTH_LONG).show();
                }
                return sb.toString();
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, EditDataBarang.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String kode = map.get("kode").toString();
        String nama = map.get("nama").toString();
        String harga = map.get("harga").toString();
        intent.putExtra("kode",kode);
        intent.putExtra("nama",nama);
        intent.putExtra("harga",harga);
        startActivity(intent);
    }

    public void Tambah(View view) {
        Intent edit=new Intent(MainActivity.this,Entry_Data_Barang.class);
        startActivity(edit);
    }
}