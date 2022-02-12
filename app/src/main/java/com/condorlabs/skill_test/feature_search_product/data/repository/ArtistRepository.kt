package com.condorlabs.skill_test.feature_search_product.data.repository

import com.condorlabs.skill_test.feature_search_product.data.remote.AlbumResponse
import com.condorlabs.skill_test.feature_search_product.data.remote.ApiCallback
import com.condorlabs.skill_test.feature_search_product.data.remote.ArtistApi
import com.condorlabs.skill_test.feature_search_product.data.remote.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ArtistRepository @Inject constructor(
    private val artistApi: ArtistApi
) {

    fun getArtists(
        nameArtist: String,
        callback: ApiCallback<SearchResponse>
    ) {

        val call = artistApi.getArtists(nameArtist, "artist")

        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onSuccess(response.body())
                } else {
                    callback.onError(response.errorBody()?.string())
                }
            }

            override fun onFailure(
                call: Call<SearchResponse>,
                t: Throwable
            ) {
                callback.onError(t.message)
            }

        })
    }

    fun getAlbums(
        nameArtist: String,
        callback: ApiCallback<AlbumResponse>
    ) {
        val call = artistApi.getAlbums(nameArtist, "album")

        call.enqueue(object : Callback<AlbumResponse> {
            override fun onResponse(
                call: Call<AlbumResponse>,
                response: Response<AlbumResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onSuccess(response.body())
                } else {
                    callback.onError(response.errorBody()?.string())
                }
            }

            override fun onFailure(
                call: Call<AlbumResponse>,
                t: Throwable
            ) {
                callback.onError(t.message)
            }

        })
    }


    private fun getUrlEvident(): String {
        return "https://api.spotify.com/v1/search?"
    }

}