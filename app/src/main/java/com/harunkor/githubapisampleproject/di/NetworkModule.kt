package com.harunkor.githubapisampleproject.di


import com.google.gson.GsonBuilder
import com.harunkor.githubapisampleproject.BuildConfig
import com.harunkor.githubapisampleproject.data.remote.ApiService
import com.harunkor.githubapisampleproject.data.remote.ServiceInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(okHttpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        OkHttpClient.Builder().apply {
            callTimeout(40, TimeUnit.SECONDS)
            connectTimeout(40,TimeUnit.SECONDS)
            readTimeout(40,TimeUnit.SECONDS)
            addInterceptor(okHttpLoggingInterceptor)
            addInterceptor(ServiceInterceptor())
            return build()
        }
    }

    @Provides
    @Singleton
    fun provideConverterFactory() : Converter.Factory {
        return  GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideRestApiService(retrofit: Retrofit): ApiService {
        return  retrofit.create(ApiService::class.java)
    }

}