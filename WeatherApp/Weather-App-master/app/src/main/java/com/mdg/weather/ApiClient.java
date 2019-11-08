package com.mdg.weather;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String baseURL = "http://api.openweathermap.org";
    private static Retrofit retrofit = null; //static so that we cannot create more than one retrofit object

    static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
