package com.example.latihanfirebase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>{

    //Deklarasi Variable
    private ArrayList<Mahasiswa> listMahasiswa;
    private Context context;
    //Membuat Interfece
    public interface dataListener{
        void onDeleteData(Mahasiswa data, int position);
    }
    //Deklarasi objek dari Interfece
    dataListener listener;
    //Membuat Konstruktor, untuk menerima input dari Database
    public MahasiswaAdapter(ArrayList listMahasiswa, Context context) {
        this.listMahasiswa = listMahasiswa;
        this.context = context;
        listener = (TampilSemuaData)context;
    }
    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView NIM, Nama;
        private LinearLayout ListItem;
        ViewHolder(View itemView) {
            super(itemView);
//Menginisialisasi View-View yang terpasang pada layout RecyclerViewkita
            NIM = itemView.findViewById(R.id.nim);
            Nama = itemView.findViewById(R.id.nama);
            ListItem = itemView.findViewById(R.id.list_item);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent,
                        false);
        return new ViewHolder(V);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final String NIM = listMahasiswa.get(position).getNim();
        final String Nama = listMahasiswa.get(position).getNama();
//Memasukan Nilai/Value kedalam View (TextView: NIM, Nama)
        holder.NIM.setText("NIM: "+NIM);
        holder.Nama.setText("Nama: "+Nama);
        holder.ListItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                final String[] action = {"Update", "Delete"};
                AlertDialog.Builder alert = new
                        AlertDialog.Builder(v.getContext());
                alert.setItems(action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                           Bundle bundle = new Bundle();
                                bundle.putString("dataNIM",
                                        listMahasiswa.get(position).getNim());
                                bundle.putString("dataNama",
                                        listMahasiswa.get(position).getNama());
                                bundle.putString("getPrimaryKey",
                                        listMahasiswa.get(position).getKey());

                                break;
                            case 1:
                                listener.onDeleteData(listMahasiswa.get(position),
                                        position);
                                break;
                        }
                    }
                });
                alert.create();
                alert.show();
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
//Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return listMahasiswa.size();
    }
}
