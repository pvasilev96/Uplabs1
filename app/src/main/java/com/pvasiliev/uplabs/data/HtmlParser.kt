package com.pvasiliev.uplabs.data

import com.pvasiliev.uplabs.data.models.Badge
import org.jsoup.Jsoup

fun parseBadges(response: String): List<Badge> {
    val document = Jsoup.parse(response)
    val elements = document.select(".user-badge")
    return elements.map {
        val (_, outerColor) = it.attr("style").removeSuffix(";").split(":")
        val element = it.selectFirst(".user-badge-inner")
        val (_, innerColor) = element.attr("style").removeSuffix(";").split(":")
        val emoji = element.selectFirst("span").text()
        Badge(emoji, innerColor, outerColor)
    }
}