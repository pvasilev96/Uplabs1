package com.pvasiliev.uplabs.di

import com.pvasiliev.uplabs.data.ColorAdapter
import com.pvasiliev.uplabs.data.LocalDateAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import java.util.*
import javax.inject.Inject
import javax.inject.Provider

class MoshiProvider @Inject constructor() : Provider<Moshi> {
    override fun get(): Moshi {
        return Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .add(LocalDateAdapter())
                .add(ColorAdapter())
                .build()
    }
}