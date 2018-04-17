package com.pvasiliev.uplabs.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.joda.time.LocalDate
import java.util.*

class LocalDateAdapter {
    @FromJson
    fun fromJson(date: Date) = LocalDate(date)

    @ToJson
    fun toJson(localDate: LocalDate) = localDate.toDate()
}