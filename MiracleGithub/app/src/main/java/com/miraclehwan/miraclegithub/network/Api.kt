package com.miraclehwan.miraclegithub.network

import com.miraclehwan.miraclegithub.network.response.RepositoryResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface Api {

    @GET("/search/repositories")
    fun getRepository(
        @Query("q") q: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Int
    ): Single<RepositoryResponse>
}