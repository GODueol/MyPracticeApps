package com.miraclehwan.translate.viewmodel

import android.util.Log
import androidx.databinding.Observable
import com.miraclehwan.translate.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class TranslateViewModel : BaseViewModel() {

    val translateSubject = PublishSubject.create<Int>()

    val mTranslateRepository = TranslateRepository()
    val mTranslateContent = TranslateContent()

    val translateCallback = object : Observable.OnPropertyChangedCallback(){
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            if (mTranslateContent.sourceText.get()!!.isNotEmpty()){
                doTranslate()
            }
        }
    }

    init{
        mTranslateContent.sourceText.addOnPropertyChangedCallback(translateCallback)
        mTranslateContent.target.addOnPropertyChangedCallback(translateCallback)

        Thread{
            while (true){
                Log.e("daehwan", "로그 / ${mTranslateContent.source.get()} -> ${mTranslateContent.target.get()} / ${mTranslateContent.sourceText.get()} / ${mTranslateContent.targetText.get()}")
                Thread.sleep(2000)
            }
        }.start()
    }

    override fun onCleared() {
        super.onCleared()
        mTranslateContent.sourceText.removeOnPropertyChangedCallback(translateCallback)
        mTranslateContent.target.removeOnPropertyChangedCallback(translateCallback)
    }

    fun doTranslate() {
        if (mTranslateContent.source.get().equals(Language.SOURCE_KOREAN.language) || mTranslateContent.target.get().equals(Language.TARGET_KOREAN.language)) {
            translateCall(
                mTranslateContent.source.get()!!,
                mTranslateContent.target.get()!!,
                mTranslateContent.sourceText.get()!!
            )
        } else {
            middelTranslateCall(
                mTranslateContent.source.get()!!,
                mTranslateContent.target.get()!!,
                mTranslateContent.sourceText.get()!!
            )
        }
    }

    private fun translateCall(source: String, target: String, content: String) {
        addDisposable(
            mTranslateRepository.getTranslateResult(source, target, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ translateResult ->
                    mTranslateContent.targetText.set(translateResult)
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

    fun updateSpinnerView(language: Language) {
        when (language.type) {
            SOURCE -> {
                mTranslateContent.sourceText.set("")
                mTranslateContent.targetText.set("")
                mTranslateContent.source.set(language.language)
            }
            TARGET -> {
                mTranslateContent.targetText.set("")
                mTranslateContent.target.set(language.language)
            }
        }
    }
}
