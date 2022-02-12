package com.condorlabs.skill_test.feature_search_product.presentation.search_artist

import com.condorlabs.skill_test.core.models.ItemAlbum

interface AlbumsListResultCallback {
    fun onItemClicked(itemAlbum: ItemAlbum)
}