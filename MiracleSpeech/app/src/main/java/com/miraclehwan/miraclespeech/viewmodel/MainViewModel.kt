package com.miraclehwan.miraclespeech.viewmodel

import android.os.Bundle
import android.os.Handler
import com.kakao.sdk.newtoneapi.SpeechRecognizeListener
import com.kakao.sdk.newtoneapi.SpeechRecognizerClient
import com.miraclehwan.miraclegithub.util.Log

class MainViewModel : BaseViewModel() {

    private val mSpeechRecognizerClient = SpeechRecognizerClient.Builder().apply {
        serviceType = SpeechRecognizerClient.SERVICE_TYPE_WEB
    }.build()

    private val mSpeechRecognizeListener = object : SpeechRecognizeListener {
        override fun onReady() {
            Log.e("")
        }

        override fun onFinished() {
            Log.e("")
        }

        override fun onPartialResult(partialResult: String?) {
            Log.e("")
        }

        override fun onBeginningOfSpeech() {
            Log.e("")
        }

        override fun onAudioLevel(audioLevel: Float) {
            Log.e("")
        }

        override fun onEndOfSpeech() {
            Log.e("")
        }

        override fun onError(errorCode: Int, errorMsg: String?) {
            Log.e("")
        }

        override fun onResults(results: Bundle?) {
            Log.e("")
        }
    }

    init {
        mSpeechRecognizerClient.setSpeechRecognizeListener(mSpeechRecognizeListener)
    }

    fun startRecording(){
        mSpeechRecognizerClient.startRecording(true);
        Handler().postDelayed({mSpeechRecognizerClient.stopRecording()}, 5000)
    }
}