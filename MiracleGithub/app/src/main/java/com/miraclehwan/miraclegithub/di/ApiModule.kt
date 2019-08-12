package com.miraclehwan.miraclegithub.di

import com.miraclehwan.miraclegithub.network.Api
import com.miraclehwan.miraclerx.Constants.API_TOKEN
import com.miraclehwan.miraclerx.Constants.BASE_URL
import com.miraclehwan.miraclerx.Constants.IS_DEBUG
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (IS_DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            }
            addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "token $API_TOKEN")?.build();
                chain.proceed(request)
            }
            connectTimeout(4, TimeUnit.SECONDS)
            readTimeout(4, TimeUnit.SECONDS)
            writeTimeout(4, TimeUnit.SECONDS)
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            client(httpClient)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
            baseUrl(BASE_URL)
        }.build()
    }

    @Module
    companion object {
        @Singleton
        @JvmStatic
        @Provides
        fun provideApi(retrofit: Retrofit): Api{
            return retrofit.create(Api::class.java)
        }
    }
}