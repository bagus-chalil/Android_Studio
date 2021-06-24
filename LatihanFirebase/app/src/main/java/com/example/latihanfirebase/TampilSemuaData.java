package com.example.latihanfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TampilSemuaData extends AppCompatActivity implements MahasiswaAdapter.dataListener {
    //Deklarasi Variable untuk RecyclerView
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;
    //Deklarasi Variable Database Reference dan ArrayList dengan Parameter Class ColorSpace.Model kita.
    private DatabaseReference reference;
    private ArrayList<Mahasiswa> dataMahasiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R .layout.activity_tampil_semua_data);
        progressBar = findViewById(R.id.proggress_bar_list);
        recyclerView = findViewById(R.id.datalist);
        MyRecyclerView();
        GetData();
    }
    //Berisi baris kode untuk mengambil data dari Database dan menampilkannya kedalam Adapter
    private void GetData(){
        // Toast.makeText(getApplicationContext(),"Mohon Tunggu Sebentar...",Toast.LENGTH_LONG).show();
        //Mendapatkan Referensi Database
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Siswa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
        //Inisialisasi ArrayList
                dataMahasiswa = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
        //Mapping data pada DataSnapshot ke dalam objek mahasiswa
                    Mahasiswa mahasiswa = snapshot.getValue(Mahasiswa.class);
        //Mengambil Primary Key, digunakan untuk proses Update dan Delete
                    mahasiswa.setKey(snapshot.getKey());
                    dataMahasiswa.add(mahasiswa);
                }
        //Inisialisasi Adapter dan data Mahasiswa dalam bentuk Array
                        adapter = new MahasiswaAdapter(dataMahasiswa, TampilSemuaData.this);
        //Memasang Adapter pada RecyclerView
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
        // Toast.makeText(getApplicationContext(),"Data Berhasil Dimuat", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            /*
            Kode ini akan dijalankan ketika ada error dan
            pengambilan data error tersebut lalu memprint error nya
            ke LogCat
            */
                Toast.makeText(getApplicationContext(),"Data Gagal Dimuat",
                        Toast.LENGTH_LONG).show();
                Log.e("MyListActivity", databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
    //Methode yang berisi kumpulan baris kode untuk mengatur RecyclerView
    private void MyRecyclerView(){
     //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
     //Membuat Underline pada Setiap Item Didalam List
        DividerItemDecoration itemDecoration = new
                DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
        recyclerView.addItemDecoration(itemDecoration);
    }
    @Override
    public void onDeleteData(Mahasiswa data, int position) {
        /*
         * Kode ini akan dipanggil ketika method onDeleteData
         * dipanggil dari adapter pada RecyclerView melalui interface.
         * kemudian akan menghapus data berdasarkan primary key dari data tersebut
         * Jika berhasil, maka akan memunculkan Toast
         */
        if(reference != null){
            reference.child("Siswa")
                    .child(data.getKey())
                    .removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(TampilSemuaData.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}