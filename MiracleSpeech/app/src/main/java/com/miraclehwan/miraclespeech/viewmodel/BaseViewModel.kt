package com.miraclehwan.miraclespeech.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val mCompositeDisposable : CompositeDisposable by lazy { CompositeDisposable() }

    fun addDisposable(disposable: Disposable){
        mCompositeDisposable.add(disposable)
    }

    override fun onCleared() {
        mCompositeDisposable.clear()
        super.onCleared()
    }
}