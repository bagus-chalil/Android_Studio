package com.example.qrcodelanjut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScanQrCode extends AppCompatActivity {
    public static TextView resultTextView;
    Button scanBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_code);

        scanBtn=findViewById(R.id.btn_scan);
        resultTextView=findViewById(R.id.result_text);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MediaBarcode.class));
            }
        });

    }
}