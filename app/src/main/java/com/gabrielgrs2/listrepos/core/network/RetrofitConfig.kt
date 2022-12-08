package com.gabrielgrs2.listrepos.core.network

import com.gabrielgrs2.listrepos.data.api.IApiCore
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideApi(retrofit: Retrofit): IApiCore = retrofit.create(IApiCore::class.java)

fun provideRetrofit(): Retrofit {
    lateinit var retrofit: Retrofit
    val httpClient = OkHttpClient.Builder()

    addConnectionTimeout(httpClient)
    addInterceptors(httpClient)
    // TODO Change this url to gradle file
    retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(httpClient.build())
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
