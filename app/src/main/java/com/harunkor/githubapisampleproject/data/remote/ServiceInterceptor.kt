package com.harunkor.githubapisampleproject.data.remote

import com.harunkor.githubapisampleproject.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val finalToken =  "Bearer "+BuildConfig.TOKEN
        return chain.proceed(chain.request().newBuilder()
            .addHeader("Authorization",finalToken)
            .build())
    }

}
