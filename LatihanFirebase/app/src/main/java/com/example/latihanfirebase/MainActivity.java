package com.example.latihanfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText nimmhs,namamhs;
    Button btn_simpan,btn_lihat;
    DatabaseReference reference;
    Mahasiswa mahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nimmhs=findViewById(R.id.editTextNim);
        namamhs=findViewById(R.id.editTextNama);

        btn_simpan=findViewById(R.id.buttonSimpan);
        btn_lihat=findViewById(R.id.buttonLihat);
        mahasiswa=new Mahasiswa();

        reference= FirebaseDatabase.getInstance().getReference().child("Siswa");
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mahasiswa.setNim(nimmhs.getText().toString().trim());
                mahasiswa.setNama(namamhs.getText().toString().trim());
                reference.push().setValue(mahasiswa);
                Toast.makeText(MainActivity.this,"Data Berhasil Tersimpan",Toast.LENGTH_LONG).show();
            }
        });

        btn_lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,TampilSemuaData.class);
                startActivity(intent);
            }
        });
    }
}