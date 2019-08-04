package com.miraclehwan.miraclespeech.viewmodel

import android.os.Bundle
import androidx.databinding.ObservableField
import com.kakao.sdk.newtoneapi.SpeechRecognizeListener
import com.kakao.sdk.newtoneapi.SpeechRecognizerClient
import com.kakao.util.helper.log.Logger
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class MainViewModel : BaseViewModel() {

    private val mSpeechRecognizerClient = SpeechRecognizerClient.Builder().apply {
        serviceType = SpeechRecognizerClient.SERVICE_TYPE_WEB
    }.build()

    private val mSpeechRecognizeListener = object : SpeechRecognizeListener {
        override fun onReady() {}
        override fun onPartialResult(partialResult: String?) {}
        override fun onBeginningOfSpeech() {}
        override fun onAudioLevel(audioLevel: Float) {}
        override fun onEndOfSpeech() {}

        override fun onFinished() {
            mIsWorking.set(false)
        }

        override fun onError(errorCode: Int, errorMsg: String?) {
            mIsError.set(true)
            mIsWorking.set(false)
        }

        override fun onResults(results: Bundle?) {
            var list = results?.getStringArrayList(SpeechRecognizerClient.KEY_RECOGNITION_RESULTS)
            list?.let {
                mResultItemList.set(list)
            }
        }
    }

    private val mBehaviorSubject = BehaviorSubject.create<Int>()
    val mIsWorking = ObservableField<Boolean>()
    val mIsError = ObservableField<Boolean>()
    val mResultItemList = ObservableField<MutableList<String>>()
    private val START_RECORD = 1
    private val CANCEL_RECORD = 2

    init {
        mSpeechRecognizerClient.setSpeechRecognizeListener(mSpeechRecognizeListener)
        addDisposable(mBehaviorSubject
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe {
                when (it) {
                    START_RECORD -> {
                        if (mSpeechRecognizerClient.startRecording(true)) {
                            mIsWorking.set(true)
                            mResultItemList.set(mutableListOf())
                            mIsError.set(false)
                        }
                    }
                    CANCEL_RECORD -> {
                        if (mIsWorking.get()!!) {
                            mSpeechRecognizerClient.cancelRecording()
                            mIsWorking.set(false)
                        }
                    }
                }
            })
    }

    fun startRecording() {
        mBehaviorSubject.onNext(START_RECORD)
    }

    fun cancelRecording() {
        mBehaviorSubject.onNext(CANCEL_RECORD)
    }
}