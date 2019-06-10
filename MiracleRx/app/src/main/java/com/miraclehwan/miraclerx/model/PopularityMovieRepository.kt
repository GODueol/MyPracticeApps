package com.miraclehwan.miraclerx.model

import com.miraclehwan.miraclerx.Constants.API_AUTH_KEY
import com.miraclehwan.miraclerx.Constants.SORT_BY
import com.miraclehwan.miraclerx.network.Api.Companion.RetrofitClient
import io.reactivex.Observable
import io.reactivex.Observable.fromIterable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun getPopularityMovie(): Observable<MovieInfo> {
    return RetrofitClient.getPopularityMovie(SORT_BY, API_AUTH_KEY)
        .toObservable()
        .subscribeOn(Schedulers.io())
        .filter { response -> response.results.size > 0 }
        .map { response -> response.results }
        .flatMap { resultList -> fromIterable(resultList) }
        .concatMap { result -> Observable.just(MovieInfo(result.title, result.backdropPath)).delay(1, TimeUnit.SECONDS) }
}