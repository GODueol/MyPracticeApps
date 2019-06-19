package com.miraclehwan.translate.model

import androidx.databinding.ObservableField

data class TranslateContent(
    val source: ObservableField<String> = ObservableField(""),
    val target: ObservableField<String> = ObservableField(""),
    val sourceText: ObservableField<String> = ObservableField(""),
    val targetText: ObservableField<String> = ObservableField("")
)