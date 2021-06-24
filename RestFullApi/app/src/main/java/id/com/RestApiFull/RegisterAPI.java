package id.com.RestApiFull;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by sulistiyanto on 07/12/16.
 */

public interface RegisterAPI {

    @FormUrlEncoded
    @POST("android/restfull/insert.php")
    Call<Value> daftar(@Field("kode") String kode,
                       @Field("nama") String nama,
                       @Field("harga") String harga);

    @GET("android/restfull/view.php")
    Call<Value> view();

    @FormUrlEncoded
    @POST("android/restfull/update.php")
    Call<Value> ubah(@Field("kode") String kode,
                     @Field("nama") String nama,
                     @Field("harga") String harga);


    @FormUrlEncoded
    @POST("android/restfull/delete.php")
    Call<Value> hapus(@Field("kode") String kode);

    @FormUrlEncoded
    @POST("android/restfull/search.php")
    Call<Value> search(@Field("search") String search);
}
