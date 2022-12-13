package com.gabrielgrs2.listrepos.core.network

import com.gabrielgrs2.listrepos.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideRetrofit(): Retrofit {
    lateinit var retrofit: Retrofit
    val httpClient = OkHttpClient.Builder()

    addConnectionTimeout(httpClient)
    addInterceptors(httpClient)
    retrofit = Retrofit.Builder()
        .client(httpClient.build())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BuildConfig.BASE_URL)
        .build()

    return retrofit
}

private fun addConnectionTimeout(httpClient: OkHttpClient.Builder) {
    httpClient.connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
}

private fun addInterceptors(httpClient: OkHttpClient.Builder) {
    httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
}
