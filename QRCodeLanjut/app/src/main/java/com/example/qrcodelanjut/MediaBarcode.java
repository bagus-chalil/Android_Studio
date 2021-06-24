package com.example.qrcodelanjut;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MediaBarcode extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scannerView;
    String kode="";
    String nama="";
    String harga="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
    }

    @Override
    public void handleResult(Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Hasil Scan");
//        builder.setMessage(result.getText());
//        AlertDialog alert1= builder.create();
//        alert1.show();
        baca_qrcode(result.getText());
    }
    @Override
    protected void onPause(){ super.onPause(); }
    protected void onResume(){
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    public void baca_qrcode(String hasil_qrcode) {

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.PUT,
                "http://192.168.100.73:8085/android/api/categories/detail.php?kode=" + hasil_qrcode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Hasil Scanning" + response.toString(), Toast.LENGTH_SHORT).show();

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            kode = jsonObject.getString("kode");
                            nama = jsonObject.getString("nama");
                            harga = jsonObject.getString("harga");
                            AlertDialog alertDialog = new AlertDialog.Builder(MediaBarcode.this).create();
                            alertDialog.setTitle("Scan Berhasil");

                            alertDialog.setMessage("kode :" + kode + "\n Nama Barang :" + nama + "\n Harga Barang :" + harga);
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}