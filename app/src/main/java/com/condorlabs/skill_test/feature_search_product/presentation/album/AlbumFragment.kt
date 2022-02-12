package com.condorlabs.skill_test.feature_search_product.presentation.album

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.condorlabs.skill_test.databinding.FragmentAlbumBinding

class AlbumFragment : Fragment() {

    private val args: AlbumFragmentArgs by navArgs()

    private lateinit var binding: FragmentAlbumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)
        binding.urlImg = args.imgAlbum
        binding.albumName.text = args.nameAlbum

        binding.buttonLogin.setOnClickListener {
            val url = args.urlAlbum
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
            it.context.startActivity(intent)
        }

        return binding.root
    }
}