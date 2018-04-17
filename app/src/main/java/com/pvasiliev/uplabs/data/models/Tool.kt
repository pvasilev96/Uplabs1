package com.pvasiliev.uplabs.data.models

data class Tool(val name: String) {
    val imageUrl: String
        get() = "https://www.uplabs.com/tools/$name.png"
}
