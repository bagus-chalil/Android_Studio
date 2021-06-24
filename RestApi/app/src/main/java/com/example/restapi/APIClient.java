package com.example.restapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class APICLient {
    public static final String BASE_URL="http://192.168.100.73:8085/";
    private static Retrofit retrofit=null;

    public static Retrofit getClient(){
        if (retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
