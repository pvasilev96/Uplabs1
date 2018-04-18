package com.pvasiliev.uplabs.di

import com.pvasiliev.uplabs.data.ColorAdapter
import com.pvasiliev.uplabs.data.LocalDateAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject
import javax.inject.Provider

class MoshiProvider @Inject constructor() : Provider<Moshi> {
    override fun get(): Moshi {
        return Moshi.Builder()
                .add(LocalDateAdapter())
                .add(ColorAdapter())
                .build()
    }
}