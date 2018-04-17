package com.pvasiliev.uplabs.di

import com.pvasiliev.uplabs.data.network.UplabsApi
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import toothpick.config.Module

class AppModule : Module() {
    init {
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java)
        bind(Moshi::class.java).toProvider(MoshiProvider::class.java)
        bind(Retrofit::class.java).toProvider(RetrofitProvider::class.java)
        bind(UplabsApi::class.java).toProvider(ApiProvider::class.java)
    }
}