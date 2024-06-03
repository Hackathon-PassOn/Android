package com.easyhz.cmc15th_hackathon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private val httpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    @MainRetrofit
    fun provideRetrofit(
        @InterceptorOkhttpClient client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("http://3.38.244.189")
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()


    @InterceptorOkhttpClient
    @Provides
    fun provideInterceptor(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
//        .addInterceptor(ServiceInterceptor())
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()
}