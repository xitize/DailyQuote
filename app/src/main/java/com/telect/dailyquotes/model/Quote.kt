package com.telect.dailyquotes.model

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("q")
    val text: String,

    @SerializedName("a")
    val author: String,

    @SerializedName("ai")
    val authorImage: String,

    @SerializedName("c")
    val characterCount: Int,

    @SerializedName("h")
    val html: String
)