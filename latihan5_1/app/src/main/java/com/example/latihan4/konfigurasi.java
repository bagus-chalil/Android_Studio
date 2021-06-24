package com.example.latihan4;

public class konfigurasi {

    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD="http://192.168.100.73:8085/android/latihan5/tambah_barang.php";
    public static final String URL_GET_ALL = "http://192.168.100.73:8085/android/latihan5/tampilsemua_barang.php";
    public static final String URL_GET_EMP = "http://192.168.100.73:8085/android/latihan5/tampil_barang.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.100.73:8085/android/latihan4/update_barang.php";
    public static final String URL_DELETE_EMP = "http://192.168.100.73:8085/android/latihan4/hapus_barang.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_KODE = "kode";
    public static final String KEY_EMP_NAMA = "nama"; //desg itu variabel untuk posisi
    public static final String KEY_EMP_HARGA = "harga"; //salary itu variabel untuk gajih

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_KODE = "kode";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_HARGA = "harga";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id";
}