package com.condorlabs.skill_test.feature_search_product.presentation.search_artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.condorlabs.skill_test.core.models.ItemAlbum
import com.condorlabs.skill_test.databinding.ItemListAlbumBinding
import com.condorlabs.skill_test.feature_search_product.commom.DataBoundListAdapter


class AlbumsListAdapter(
    private val albumsListResultCallback: AlbumsListResultCallback,
) :
    DataBoundListAdapter<ItemAlbum, ItemListAlbumBinding>(AlbumDiffCallback()) {

    override fun createBinding(parent: ViewGroup): ItemListAlbumBinding {
        return ItemListAlbumBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bind(binding: ItemListAlbumBinding, item: ItemAlbum) {
        binding.album = item
        binding.urlImgAlbum = item.images[0].url

        binding.availableMarkets = availableMarkets(item)

        binding.setOnItemClickListener {
            binding.album?.let { itemAlbum ->
                albumsListResultCallback.onItemClicked(itemAlbum)
            }
        }
    }

    private fun availableMarkets(item: ItemAlbum): String {
        var availableCountries = ""
        if (item.available_markets.size < 5) {
            for (country in item.available_markets) {
                availableCountries += if (availableCountries == "") {
                    country
                } else {
                    ", $country"
                }
            }
        }
        return availableCountries
    }

    private class AlbumDiffCallback : DiffUtil.ItemCallback<ItemAlbum>() {
        override fun areItemsTheSame(oldItem: ItemAlbum, newItem: ItemAlbum): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.available_markets == newItem.available_markets &&
                    oldItem.external_urls == newItem.external_urls &&
                    oldItem.images == newItem.images &&
                    oldItem.total_tracks == newItem.total_tracks
        }

        override fun areContentsTheSame(oldItem: ItemAlbum, newItem: ItemAlbum): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.available_markets == newItem.available_markets &&
                    oldItem.external_urls == newItem.external_urls &&
                    oldItem.images == newItem.images &&
                    oldItem.total_tracks == newItem.total_tracks
        }
    }
}