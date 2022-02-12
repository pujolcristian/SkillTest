package com.condorlabs.skill_test.feature_search_product.data.remote

import com.condorlabs.skill_test.core.models.Album
import com.condorlabs.skill_test.core.models.Artist

data class SearchResponse(var artists: Artist)

data class AlbumResponse(var albums: Album)