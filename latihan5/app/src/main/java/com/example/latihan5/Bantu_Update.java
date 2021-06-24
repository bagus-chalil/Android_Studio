package com.example.latihan5;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Bantu_Update extends AsyncTask<String,Void,Void> {
    Context context;
    public Bantu_Update(Context ct) {
        this.context=ct;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Void doInBackground(String... params) {
        String urlu="http://192.168.100.73:8085/android/latihan4/update_data.php";
        String kode=params[1];
        String nama=params[2];
        String harga=params[3];
        try {
            URL url=new URL(urlu);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os=null;

            os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            String data =
                    URLEncoder.encode("kode", "UTF-8") + "=" + URLEncoder.encode(kode, "UTF-8") + "&" +
                    URLEncoder.encode("nama", "UTF-8") + "=" + URLEncoder.encode(nama, "UTF-8") + "&" +
                    URLEncoder.encode("harga", "UTF-8") + "=" + URLEncoder.encode(harga, "UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();
            InputStream IS = httpURLConnection.getInputStream();
            IS.close();
        }catch (IOException e){
            e.printStackTrace();
        }


        return null;
    }
}
