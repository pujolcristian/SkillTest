package com.condorlabs.skill_test.feature_search_product.data.remote

import com.condorlabs.skill_test.PreferencesHelper
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor(
    private val apiNoneAuthService: ApiNoneAuthService,
    private val preferencesHelper: PreferencesHelper
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val grant = "client_credentials"

        val call = apiNoneAuthService.accessToken("https://accounts.spotify.com/api/token", grant)
        val result = call.execute()

        return if (result.code() == 200) {
            val accessToken = result.body()
            preferencesHelper.saveAccessToken(accessToken?.accessToken ?: "")

            response.request.newBuilder()
                .header("Authorization", "Bearer " + accessToken?.accessToken)
                .build()
        } else {
            null
        }

    }
}