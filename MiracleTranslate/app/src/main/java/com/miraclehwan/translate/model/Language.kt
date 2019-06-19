package com.miraclehwan.translate.model

const val SOURCE = true
const val TARGET = false

enum class Language(val type: Boolean, val language: String) {
    SOURCE_KOREAN(SOURCE, "ko"),
    SOURCE_ENGLISH(SOURCE, "en"),
    SOURCE_JAPAN(SOURCE, "ja"),
    SOURCE_CHINA(SOURCE, "zh-CN"),
    TARGET_KOREAN(TARGET, "ko"),
    TARGET_ENGLISH(TARGET, "en"),
    TARGET_JAPAN(TARGET, "ja"),
    TARGET_CHINA(TARGET, "zh-CN")
}