package com.example.registeruserwithserverapi;

import com.example.registeruserwithserverapi.responses.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static String BASE_URL="http://192.168.0.110/UserApi/";

    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;

    public RetrofitClient() {
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

    }
    public static synchronized RetrofitClient getInstance()
    {
        if(retrofitClient==null)
        {
            retrofitClient=new RetrofitClient();
        }
        return retrofitClient;
    }

    public Api getApi()
    {
        return retrofit.create(Api.class);

    }
}
