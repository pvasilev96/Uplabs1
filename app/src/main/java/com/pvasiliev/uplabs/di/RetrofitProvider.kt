package com.pvasiliev.uplabs.di

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class RetrofitProvider @Inject constructor(private val okHttpClient: OkHttpClient, private val moshi: Moshi) : Provider<Retrofit> {
    override fun get(): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://www.uplabs.com")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}