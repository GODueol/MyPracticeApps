package com.miracle.dagger.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        @BaseURL baseURL : String
    ): Retrofit {
        return Retrofit.Builder().apply {
            client(okHttpClient)
            addConverterFactory(gsonConverterFactory)
            baseUrl(baseURL)
        }.build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(@InterceptorType("LOG") loggingInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            connectTimeout(4, TimeUnit.SECONDS)
            readTimeout(4, TimeUnit.SECONDS)
            writeTimeout(4, TimeUnit.SECONDS)
        }.build()
    }

    @Singleton
    @Provides
    @InterceptorType("LOG")
    fun provideLogger(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson);
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
}