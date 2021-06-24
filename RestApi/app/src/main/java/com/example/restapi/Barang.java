package com.example.restapi;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Barang implements Serializable {
    @SerializedName("kode")
    String kode;
    @SerializedName("nama")
    String nama;
    @SerializedName("harga")
    String harga;

    public String getKode() {
        return kode;
    }

    public Barang(String kode, String nama, String harga) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}

