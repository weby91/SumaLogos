package com.sumalogos.webster.sumalogos.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by webster on 01/01/18.
 */

object APIClient {
    val client: Retrofit
        get() {
            val retrofit: Retrofit

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                    .connectTimeout(AppConstant.TIME_OUT_LIMIT.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(AppConstant.TIME_OUT_LIMIT.toLong(), TimeUnit.SECONDS)
                    .readTimeout(AppConstant.TIME_OUT_LIMIT.toLong(), TimeUnit.SECONDS)
                    .hostnameVerifier { _, _ -> true }
                    .build()

            retrofit = Retrofit.Builder()
                    .baseUrl(AppConstant.BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()

            return retrofit
        }
}