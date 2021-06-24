package com.example.restapi;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceBarang {
    @GET("android/restapi/")
    Call<List<Barang>> getBarang();
    @FormUrlEncoded
    @POST("android/restapi/")
    Call<Barang> postBarang(@Field("kode") String kode,
                            @Field("nama") String nama,
                            @Field("harga") String harga);
    @DELETE("android/restapi/")
    Call<Barang> deleteBarang(@Query("kode") String kode);
}
