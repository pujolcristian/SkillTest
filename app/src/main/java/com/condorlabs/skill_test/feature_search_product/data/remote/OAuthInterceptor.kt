package com.condorlabs.skill_test.feature_search_product.data.remote

import com.condorlabs.skill_test.PreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OAuthInterceptor @Inject constructor(
    private val preferencesHelper: PreferencesHelper
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val authorisedRequestBuilder = originalRequest.newBuilder()
            .header("Authorization", "Bearer " + preferencesHelper.getAccessToken())
            .header("Accept", "application/json")

        return chain.proceed(authorisedRequestBuilder.build())
    }

}