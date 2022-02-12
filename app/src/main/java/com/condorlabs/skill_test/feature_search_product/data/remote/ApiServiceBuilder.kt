package com.condorlabs.skill_test.feature_search_product.data.remote

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiServiceBuilder {

    companion object {
        private const val CONNECTION_TIMEOUT = 120L

        fun <T> builder(
            url: String,
            serviceClass: Class<T>,
            interceptors: Array<Interceptor>,
            authenticator: Authenticator?
        ): T {
            val okHttpClientBuilder = OkHttpClient().newBuilder()

            for (interceptor in interceptors) {
                //     if (interceptor is HttpLoggingInterceptor && BuildConfig.DEBUG)
                okHttpClientBuilder.addInterceptor(interceptor)
            }

            okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            okHttpClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)

            if (authenticator != null) {
                okHttpClientBuilder.authenticator(authenticator)
            }
            val okHttpClient = okHttpClientBuilder.build()

            val retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(
                GsonConverterFactory.create()
            )

                .client(okHttpClient)
                .build()

            return retrofit.create(serviceClass)
        }
    }
}