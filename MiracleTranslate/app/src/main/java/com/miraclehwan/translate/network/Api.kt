package com.miraclehwan.translate.network

import com.miraclehwan.translate.Constants.BASE_URL
import com.miraclehwan.translate.Constants.IS_DEBUG
import com.miraclehwan.translate.Constants.NAVER_CLIENT_ID
import com.miraclehwan.translate.Constants.NAVER_CLIENT_SECRECT
import com.miraclehwan.translate.model.TranslateResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface Api {

    @POST("/v1/language/translate")
    @FormUrlEncoded
    fun translate(
        @Field("source") source: String,
        @Field("target") target: String,
        @Field("text") text: String
    ): Single<TranslateResponse>

    companion object {
        val RetrofitClient: Api by lazy {
            val okHttpClient = OkHttpClient.Builder().apply {
                addInterceptor { chain ->
                    chain.proceed(
                        chain.request().newBuilder().apply {
                            addHeader("X-Naver-Client-Id", NAVER_CLIENT_ID)
                            addHeader("X-Naver-Client-Secret", NAVER_CLIENT_SECRECT)
                        }.build()
                    )
                }
                if (IS_DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                }
                connectTimeout(4, TimeUnit.SECONDS)
                readTimeout(4, TimeUnit.SECONDS)
                writeTimeout(4, TimeUnit.SECONDS)
            }.build()

            val retrofit = Retrofit.Builder().apply {
                client(okHttpClient)
                addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(BASE_URL)
            }.build()

            retrofit.create(Api::class.java)
        }

//todo::이전 RetorfitClient 코드 :: 20190513
//        fun create(): Api {
//            val okHttpClient by lazy {
//                OkHttpClient.Builder().apply {
//                    addInterceptor { chain ->
//                        chain.proceed(
//                            chain.request().newBuilder().apply {
//                                addHeader("X-Naver-Client-Id", NAVER_CLIENT_ID)
//                                addHeader("X-Naver-Client-Secret", NAVER_CLIENT_SECRECT)
//                            }.build()
//                        )
//                    }
//                    if (IS_DEBUG) {
//                        addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
//                    }
//                    connectTimeout(4, TimeUnit.SECONDS)
//                    readTimeout(4, TimeUnit.SECONDS)
//                    writeTimeout(4, TimeUnit.SECONDS)
//                }.build()
//            }
//
//            val retrofit by lazy {
//                Retrofit.Builder().apply {
//                    client(okHttpClient)
//                    addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    addConverterFactory(GsonConverterFactory.create())
//                    baseUrl(BASE_URL)
//                }.build()
//            }
//
//            return retrofit.create(Api::class.java)
//        }
    }
}