package com.miraclehwan.translate.model

import com.miraclehwan.translate.network.Api
import io.reactivex.Single

class TranslateRepository {
    fun getTranslateResult(source: String, target: String, content: String): Single<String> {
        return Api.RetrofitClient
            .translate(source, target, content)
            .map { it?.message?.result?.translatedText }
    }
}