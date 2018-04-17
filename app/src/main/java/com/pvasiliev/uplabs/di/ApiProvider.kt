package com.pvasiliev.uplabs.di

import com.pvasiliev.uplabs.data.network.UplabsApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class ApiProvider @Inject constructor(private val retrofit: Retrofit) : Provider<UplabsApi> {
    override fun get(): UplabsApi {
        return retrofit.create(UplabsApi::class.java)
    }
}