package com.example.edulog.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static HttpLoggingInterceptor.Level HTTPLogLevel = HttpLoggingInterceptor.Level.BODY;
    private ApiInterface apiInterface;
    private static final int connectTimeOut = 2, readTimeOut = 3, writeTimeOut = 120;
    private static final String BASE_URL = "https://eduvriksh.com/managepro/api/";

    public APIClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HTTPLogLevel);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(connectTimeOut, TimeUnit.MINUTES)
                .readTimeout(readTimeOut, TimeUnit.MINUTES)
                .writeTimeout(writeTimeOut, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }

}
