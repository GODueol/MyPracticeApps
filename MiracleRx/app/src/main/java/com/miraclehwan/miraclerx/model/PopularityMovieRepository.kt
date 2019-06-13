package com.miraclehwan.miraclerx.model

import com.miraclehwan.miraclerx.Constants.API_AUTH_KEY
import com.miraclehwan.miraclerx.Constants.LANGUAGE
import com.miraclehwan.miraclerx.Constants.SORT_BY
import com.miraclehwan.miraclerx.network.Api.Companion.RetrofitClient
import com.miraclehwan.miraclerx.network.response.Genre
import com.miraclehwan.miraclerx.network.response.Result
import io.reactivex.Observable
import io.reactivex.Observable.fromIterable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun getPopularityMovieList(): Single<List<Result>> {
    return RetrofitClient.getPopularityMovie(SORT_BY, API_AUTH_KEY)
        .subscribeOn(Schedulers.io())
        .map { response -> response.results }
}

fun getGenre(): Single<List<Genre>> {
    return RetrofitClient.getGenre(API_AUTH_KEY, LANGUAGE)
        .subscribeOn(Schedulers.io())
        .map { response -> response.genres }
}

fun convertIntegerToString(intList: List<Int>, genreIdList: List<Genre>): List<String> {
    val genreList = ArrayList<String>()
    for (int in intList) {
        for (genre in genreIdList) {
            if (int == genre.id) {
                genreList.add(genre.name)
                continue
            }
        }
    }
    return genreList
}

fun getMovie(): Observable<Movie> {
    return Single.zip(
        getPopularityMovieList(),
        getGenre(),
        BiFunction<List<Result>, List<Genre>, List<Movie>> { resultList, genreList ->
            val movieList = ArrayList<Movie>()
            for (result in resultList) {
                movieList.add(
                    Movie(
                        result.title,
                        result.backdropPath,
                        result.overview,
                        convertIntegerToString(result.genreIds, genreList)
                    )
                )
            }
            movieList
        })
        .subscribeOn(Schedulers.io())
        .toObservable()
        .concatMap { movie -> fromIterable(movie) }
        .concatMap { movie ->
            Observable
                .just(movie)
                .delay(1, TimeUnit.SECONDS)
        }
}