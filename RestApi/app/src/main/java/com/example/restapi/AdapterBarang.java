package com.example.restapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.MyViewHolder> {

    private ArrayList<Barang> listbarang;
    public AdapterBarang(ArrayList<Barang> listbarang) {
        this.listbarang = listbarang;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        // Get position of MyViewHolder class
        Barang barang=listbarang.get(position);
        holder.kd_brg.setText(listbarang.get(position).getKode());
        holder.nm_brg.setText(listbarang.get(position).getNama());
        holder.hrg_brg.setText(listbarang.get(position).getHarga());

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Context context=holder.itemView.getContext();
                Intent intent = new Intent(view.getContext(), UbahHapusData.class);
                // Initialize to bind data for detail_activity
                intent.putExtra("kode", listbarang.get(position).getKode());
                intent.putExtra("nama", listbarang.get(position).getNama());
                intent.putExtra("harga", listbarang.get(position).getHarga());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listbarang.size();
    }

    // This ViewHolder of RecycleView
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView kd_brg,nm_brg,hrg_brg;
        public MyViewHolder(View itemView) {
            super(itemView);

            // Find ID of layout
            kd_brg = itemView.findViewById(R.id.kode_brg);
            nm_brg = itemView.findViewById(R.id.nama_brg);
            hrg_brg = itemView.findViewById(R.id.harga_brg);
        }
    }
}
