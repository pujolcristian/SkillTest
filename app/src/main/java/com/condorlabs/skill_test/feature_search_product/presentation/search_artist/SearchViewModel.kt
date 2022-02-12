package com.condorlabs.skill_test.feature_search_product.presentation.search_artist

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.condorlabs.skill_test.R
import com.condorlabs.skill_test.core.models.Item
import com.condorlabs.skill_test.core.models.ItemAlbum
import com.condorlabs.skill_test.feature_search_product.data.remote.AlbumResponse
import com.condorlabs.skill_test.feature_search_product.data.remote.ApiCallback
import com.condorlabs.skill_test.feature_search_product.data.remote.SearchResponse
import com.condorlabs.skill_test.feature_search_product.data.repository.ArtistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val artistRepository: ArtistRepository) :
    ViewModel() {

    val nameArtist = MediatorLiveData<String>()

    private val _dataArtist = MediatorLiveData<MutableList<Item>>()
    val dataArtist: LiveData<MutableList<Item>> = _dataArtist

    private val _dataAlbums = MediatorLiveData<MutableList<ItemAlbum>>()
    val dataAlbums: LiveData<MutableList<ItemAlbum>> = _dataAlbums

    private val _isLoading = MediatorLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _onMessageError = MediatorLiveData<String?>()
    val onMessageError: LiveData<String?> = _onMessageError


    private fun searchArtist(view: View) {
        _isLoading.value = true

        artistRepository.getArtists(nameArtist.value!!,
            object : ApiCallback<SearchResponse> {
                override fun onSuccess(data: SearchResponse?) {
                    _isLoading.postValue(false)
                    if (data != null) {
                        if (!data.artists.items.isNullOrEmpty()) {
                            _dataArtist.value = data.artists.items
                            getAlbums(data.artists.items[0].name)
                        } else {
                            _onMessageError.postValue(view.context.getString(R.string.artist_no_found))
                        }
                    }
                }

                override fun onError(error: String?) {
                    _isLoading.postValue(false)
                    _onMessageError.postValue(error)
                }
            })
    }

    private fun getAlbums(name: String) {
        artistRepository.getAlbums(name,
            object : ApiCallback<AlbumResponse> {

                override fun onSuccess(data: AlbumResponse?) {
                    _isLoading.postValue(false)
                    if (data != null) {
                        _dataAlbums.value = data.albums.items
                    }
                }

                override fun onError(error: String?) {
                    _isLoading.postValue(false)
                    _onMessageError.postValue(error)
                }

            })
    }

    fun onSearchClick(view: View) {
        if (isValidForm(view.context)){
            searchArtist(view)
        }
    }

    private fun isValidForm(context: Context): Boolean {
        val textSearch = nameArtist.value?.trim()

        if (textSearch.isNullOrEmpty()) {
            Toast.makeText(
                context,
                context.getString(R.string.enter_artist_name),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }
}