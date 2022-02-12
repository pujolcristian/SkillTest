package com.condorlabs.skill_test.feature_search_product.presentation.search_artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.condorlabs.skill_test.R
import com.condorlabs.skill_test.core.models.Item
import com.condorlabs.skill_test.core.models.ItemAlbum
import com.condorlabs.skill_test.databinding.FragmentSearchBinding
import com.condorlabs.skill_test.feature_search_product.commom.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment(), AlbumsListResultCallback {


    private lateinit var binding: FragmentSearchBinding

    private val searchViewModel: SearchViewModel by viewModels()

    private lateinit var adapter: AlbumsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = searchViewModel

        adapter = AlbumsListAdapter(this)
        binding.recyclerViewAlbums.adapter = adapter

        searchViewModel.dataArtist.observe(viewLifecycleOwner, dataArtistObserver)
        searchViewModel.isLoading.observe(viewLifecycleOwner, isLoadingObserver)
        searchViewModel.onMessageError.observe(viewLifecycleOwner, messageErrorObserver)
        searchViewModel.dataAlbums.observe(viewLifecycleOwner, dataAlbumsObserver)

        return binding.root
    }

    private val dataArtistObserver = Observer<MutableList<Item>> { dataArtists ->
        if (viewLifecycleOwner.lifecycle.currentState == lifecycle.currentState) {
            if (!dataArtists.isNullOrEmpty()) {
                binding.artist = dataArtists[0]
                binding.urlImg = dataArtists[0].images[0].url
            }
        }
    }

    private val dataAlbumsObserver = Observer<MutableList<ItemAlbum>> { dataAlbums ->
        adapter.submitList(dataAlbums)
    }

    private val isLoadingObserver: Observer<Boolean> = Observer { isLoading ->
        if (isLoading) {
            showDialogLoading()
        } else {
            dismissDialogLoading()
        }
    }

    private val messageErrorObserver: Observer<String?> = Observer {
        if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
            val errorMessage = it
                ?: requireContext().getString(R.string.your_request_could_not_processed_try_later)
            showInfoDialog(errorMessage)
        }
    }

    override fun onItemClicked(itemAlbum: ItemAlbum) {
        val name = itemAlbum.name
        val img = itemAlbum.images[0].url
        val url = itemAlbum.external_urls.spotify
        findNavController().navigate(
            SearchFragmentDirections.navigateToAlbumFragment(
                name,
                img,
                url
            )
        )
    }
}