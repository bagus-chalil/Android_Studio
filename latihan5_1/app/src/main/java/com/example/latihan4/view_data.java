package com.example.latihan4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Provider;

public class view_data extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        listView=(ListView) findViewById(R.id.listdata);
        tampilkanbarang("http://192.168.100.73:8085/android/latihan4/tampil_data.php");
    }

    private void tampilkanbarang(final String urlWeb) {
        class Tampilkanbarang extends AsyncTask<Void, Void, String>{

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try{
                    read_barang(s);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWeb);
                    HttpURLConnection koneksi = (HttpURLConnection) url.openConnection();
                    StringBuilder sb =  new StringBuilder();
                    BufferedReader br = new BufferedReader(new InputStreamReader(koneksi.getInputStream()));
                    String json;
                    while ((json = br.readLine()) !=null){
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();
                }catch (Exception e){
                    return null;
                }
            }
        }
        Tampilkanbarang tampil= new Tampilkanbarang();
        tampil.execute();
    }

    private void read_barang(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] stocks = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            stocks[i] = obj.getString("kode") + " -> " +
                    obj.getString("nama") + " -> " +
                    obj.getString("harga");
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,stocks);
        listView.setAdapter(arrayAdapter);
    }
}