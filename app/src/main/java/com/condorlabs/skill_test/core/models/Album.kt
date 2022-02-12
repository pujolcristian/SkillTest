package com.condorlabs.skill_test.core.models

data class Album(
    val items: MutableList<ItemAlbum>
)

data class ItemAlbum(
    val name: String,
    val available_markets: MutableList<String>,
    val images: MutableList<Image>,
    val total_tracks: Int,
    val external_urls: ExternalUrl
)