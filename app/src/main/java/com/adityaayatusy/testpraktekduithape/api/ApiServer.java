package com.adityaayatusy.testpraktekduithape.api;;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServer {

    public static final String BASE_URL = "http://integrator-dev.duithape.com/api/backoffice/";
    public static Retrofit retrofit;

    public Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }
}