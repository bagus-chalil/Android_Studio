package com.example.latihanfirebase;

public class Mahasiswa {
    private String nim;
    private String nama;
    private String key;

    public Mahasiswa(String nim, String nama, String key) {
        this.nim = nim;
        this.nama = nama;
        this.key = key;
    }

    public Mahasiswa() {
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
