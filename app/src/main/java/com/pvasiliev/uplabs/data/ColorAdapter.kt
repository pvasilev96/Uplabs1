package com.pvasiliev.uplabs.data

import com.pvasiliev.uplabs.data.models.Color
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class ColorAdapter {
    @FromJson
    fun fromJson(palette: List<List<String>>) =
            palette.map {
                val (name, hexValue) = it
                Color(name, hexValue)
            }

    @ToJson
    fun toJson(palette: List<Color>) =
            palette.map {
                listOf(it.name, it.hexValue)
            }
}