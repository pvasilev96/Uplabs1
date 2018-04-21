package com.pvasiliev.uplabs.data.models

import com.squareup.moshi.Json
import org.joda.time.LocalDate

data class Post(
        val id: Long,
        @Json(name = "animated_teaser_url") val teaser: String,
        @Json(name = "name") val title: String,
        @Json(name = "description_without_html") val description: String,
        @Json(name = "link_url") val link: String,
        @Json(name = "category_friendly_name") val category: String,
        @Json(name = "subcategory_friendly_name_plural") val subcategory: String,
        @Json(name = "label_friendly_name") val label: String,
        @Json(name = "showcased_at") val date: LocalDate,
        @Json(name = "serialized_submitter") val creator: User,
        @Json(name = "serialized_upvoters") val upvoters: List<User>,
        @Json(name = "serialized_tools") val tools: List<Tool>,
        @Json(name = "palette") val colors: List<Color>,
        @Json(name = "view_count") val views: Int,
        @Json(name = "comments_count") val comments: Int,
        @Json(name = "points") val upvotes: Int
)

