package com.condorlabs.skill_test.feature_search_product.data.remote

import com.condorlabs.skill_test.core.models.Token
import retrofit2.Call
import retrofit2.http.*

interface ApiNoneAuthService {


    @Headers("Authorization: Basic NmRmM2VmYzE2ZTY0NDA0OThhODUyYTEzM2IyYWUwN2I6NDI4N2U5OWM1MTc0NDU4Mjg5ZTQ1ODk4NWE3NjAxYzU=")
    @POST
    @FormUrlEncoded
    fun accessToken(
        @Url url: String,
        @Field("grant_type") grantType: String
    ): Call<Token>
}