package com.miraclehwan.miraclegithub.model

import com.miraclehwan.miraclegithub.network.Api
import com.miraclehwan.miraclegithub.network.RetrofitClient
import com.miraclehwan.miraclegithub.network.response.Item
import com.miraclehwan.miraclerx.Constants.ORDER_BY
import com.miraclehwan.miraclerx.Constants.SORT
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class SearchRepository {

    var totalItemCount = 0

    fun getRepository(q: String, page: Int): Single<List<Item>> {
        return RetrofitClient().getApi.getRepository(q, SORT, ORDER_BY, page)
            .subscribeOn(Schedulers.io())
            .map { res ->
                totalItemCount = res.totalCount!!
                res.items
            }
    }
}