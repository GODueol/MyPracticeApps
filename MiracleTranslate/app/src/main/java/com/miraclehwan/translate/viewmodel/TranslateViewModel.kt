package com.miraclehwan.translate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miraclehwan.translate.network.Api.Companion.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TranslateViewModel : ViewModel() {

    val mTranslateResultLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun doTranslate(source: String, target: String, content: String) {
        if (source.equals("한국어") || target.equals("한국어")) {
            translateCall(getLanguageCode(source), getLanguageCode(target), content)
        } else {
            middelTranslateCall(getLanguageCode(source), getLanguageCode(target), content)
        }
    }

    private fun translateCall(source: String, target: String, content: String) {
        RetrofitClient
            .translate(source, target, content)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val resultString = it?.message?.result?.translatedText ?: null
                if (resultString != null) {
                    mTranslateResultLiveData.value = resultString
                }
            }, {})
    }

    private fun middelTranslateCall(source: String, target: String, content: String) {
        RetrofitClient
            .translate(source, "ko", content)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                val resultString = it?.message?.result?.translatedText ?: null
                if (resultString != null) {
                    translateCall("ko", target, resultString)
                }
            }, {})
    }

    private fun getLanguageCode(selectedLanguage: String): String {
        when (selectedLanguage) {
            "한국어" -> return "ko"
            "영어" -> return "en"
            "일본어" -> return "ja"
            "중국어" -> return "zh-CN"
            else -> return "ko"
        }
    }
}
