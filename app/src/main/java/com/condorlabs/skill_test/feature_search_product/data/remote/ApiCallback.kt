package com.condorlabs.skill_test.feature_search_product.data.remote

interface ApiCallback<T> {

    fun onSuccess(data: T?)
    fun onError(error: String?)

}