package com.miraclehwan.miraclegithub.model

import com.miraclehwan.miraclegithub.App
import com.miraclehwan.miraclegithub.network.Api
import com.miraclehwan.miraclegithub.network.response.Item
import com.miraclehwan.miraclerx.Constants.ORDER_BY
import com.miraclehwan.miraclerx.Constants.SORT
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchRepository {

    var totalItemCount = 0

    @Inject
    lateinit var mApi: Api

    init {
        App.mInstance.mDaggerApiComponent.inject(this)
    }

    fun getRepository(q: String, page: Int): Single<List<Item>> {
        return mApi.getRepository(q, SORT, ORDER_BY, page)
            .subscribeOn(Schedulers.io())
            .map { res ->
                totalItemCount = res.totalCount!!
                res.items
            }
    }
}