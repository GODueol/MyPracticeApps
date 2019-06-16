package com.miraclehwan.miraclegithub.viewmodel

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.miraclehwan.miraclegithub.model.SearchRepository
import com.miraclehwan.miraclegithub.network.response.Item
import com.miraclehwan.miraclegithub.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class MainViewModel : BaseViewModel() {
    val input = ObservableField<String>()
    val items = ObservableField<List<Item>>()

    val searchRepository = SearchRepository()
    var currentPage = 1
    val pageSubject = PublishSubject.create<Int>()
    var loading = false

    val searchPropertyChangedCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            items.set(mutableListOf())
            if (input.get() == null) {
                return
            }
            currentPage = 1
            pageSubject.onNext(currentPage)
        }
    }

    init {
        input.addOnPropertyChangedCallback(searchPropertyChangedCallback)
        addDisposable(
            pageSubject
                .debounce(500, TimeUnit.MILLISECONDS)
                .doOnNext { loading = true }
                .switchMapSingle { page ->
                    searchRepository.getRepository(input.get()!!, page)
                        .subscribeOn(Schedulers.io())
                        .onErrorReturn {
                            mutableListOf()
                        }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ itemList ->
                    items.set(itemList)
                    loading = false
                }, { e ->
                    Log.e("Fail - ${e}")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        input.removeOnPropertyChangedCallback(searchPropertyChangedCallback)
    }
}