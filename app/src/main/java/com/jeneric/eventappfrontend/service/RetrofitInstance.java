package com.jeneric.eventappfrontend.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit = null;

     private final static String BASE_URL = "http://10.0.2.2:8080/api/"; // if running from simulated device within Android Studio

//    private final static String BASE_URL = "http://192.168.1.34:8080/api/"; // if running from real device (IP address to PC-hosted API may change)

    public static EventApiService getService() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOKHttpClient())
                    .build();
        }
        return retrofit.create(EventApiService.class);
    }


    private static OkHttpClient getOKHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();
    }
}