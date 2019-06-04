package com.miraclehwan.translate.model

import com.google.gson.annotations.SerializedName

data class TranslateResponse(
    @SerializedName("message")
    val message: Message
)

data class Message(
    @SerializedName("@type")
    val type: String,
    @SerializedName("@service")
    val service: String,
    @SerializedName("@version")
    val version: String,
    @SerializedName("result")
    val result: Result
)

data class Result(
    @SerializedName("translatedText")
    val translatedText: String,
    @SerializedName("srcLangType")
    val srcLangType: String
)

