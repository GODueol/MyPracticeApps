package com.miraclehwan.translate.viewmodel

import androidx.lifecycle.MutableLiveData
import com.miraclehwan.translate.model.TranslateRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TranslateViewModel : BaseViewModel() {

    val mTranslateResultLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val mTranslateRepository = TranslateRepository()

    fun doTranslate(source: String, target: String, content: String) {
        if (source.equals("한국어") || target.equals("한국어")) {
            translateCall(getLanguageCode(source), getLanguageCode(target), content)
        } else {
            middelTranslateCall(getLanguageCode(source), getLanguageCode(target), content)
        }
    }

    private fun translateCall(source: String, target: String, content: String) {
        addDisposable(
            mTranslateRepository.getTranslateResult(source, target, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ translateResult ->
                    mTranslateResultLiveData.value = translateResult
                }, {})
        )
    }

    private fun middelTranslateCall(source: String, target: String, content: String) {
        addDisposable(
            mTranslateRepository.getTranslateResult(source, "ko", content)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({ translateResult ->
                    translateCall("ko", target, translateResult)
                }, {})
        )
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
