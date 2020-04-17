package com.rosario.covid19.data

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.*


@Singleton
class CustomInterceptor @Inject constructor(var context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val originalHttpUrl = original.url()
        val requestBuilder = original.newBuilder()
            .addHeader("X-RapidAPI-Key", "96afa298cbmsh913f910f914494cp110c39jsn01a32d68445e")
            .url(originalHttpUrl)

        val request = requestBuilder.build()
        return chain.proceed(request)

    }
}
