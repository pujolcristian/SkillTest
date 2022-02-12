package com.condorlabs.skill_test.feature_search_product.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ArtistApi {

    @GET("search")
    fun getArtists(
        @Query("q") q: String,
        @Query("type") type: String
    ): Call<SearchResponse>

    @GET("search")
    fun getAlbums(
        @Query("q") q: String,
        @Query("type") type: String
    ): Call<AlbumResponse>

    companion object {
        const val BASE_URL = "https://api.spotify.com/v1/"
    }

}