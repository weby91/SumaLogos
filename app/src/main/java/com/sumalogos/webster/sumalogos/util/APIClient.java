package com.sumalogos.webster.sumalogos.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by webster on 18/01/18.
 */

public class APIClient {
    public static Retrofit getClient() {
        Retrofit retrofit;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(AppConstant.TIME_OUT_LIMIT, TimeUnit.SECONDS)
                .writeTimeout(AppConstant.TIME_OUT_LIMIT, TimeUnit.SECONDS)
                .readTimeout(AppConstant.TIME_OUT_LIMIT, TimeUnit.SECONDS)
                .hostnameVerifier((hostname, session) -> true)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
