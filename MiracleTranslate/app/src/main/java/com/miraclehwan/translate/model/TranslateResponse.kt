package com.miraclehwan.translate.model

import com.google.gson.annotations.SerializedName

class TranslateResponse {

    @SerializedName("message")
    val message: Message? = null

    inner class Message {

        @SerializedName("@type")
        val type: String? = null
        @SerializedName("@service")
        val service: String? = null
        @SerializedName("@version")
        val version: String? = null
        @SerializedName("result")
        val result: Result? = null

        inner class Result {
            @SerializedName("translatedText")
            val translatedText: String? = null
            @SerializedName("srcLangType")
            val srcLangType: String? = null
        }
    }
}

