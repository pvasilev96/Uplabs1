package com.pvasiliev.uplabs.data.models

import com.squareup.moshi.Json

data class User(
        val id: Long,
        @Json(name = "first_name") val firstName: String,
        @Json(name = "full_name") val fullName: String,
        @Json(name = "avatar_url") val avatar: String
)