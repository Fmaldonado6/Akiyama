package com.fmaldonado.akiyama.ui.activities.main.fragments.favorites

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.databinding.FragmentFavoritesBinding
import com.fmaldonado.akiyama.ui.activities.detail.DetailActivity
import com.fmaldonado.akiyama.ui.activities.filterFavorites.FilterFavorites
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.fmaldonado.akiyama.ui.common.Status
import com.fmaldonado.akiyama.ui.common.adapters.SearchResultAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchBar.setOnClickListener {
            val intent = Intent(requireContext(), FilterFavorites::class.java)
            startActivity(intent)
        }

        viewModel.favorites.observe(viewLifecycleOwner) {
            if (it.isEmpty()) binding.currentStatus = Status.Empty
            else binding.currentStatus = Status.Loaded
            setupFavorites(it)
        }
    }

    private fun setupFavorites(favorites: List<Anime>) {
        val adapter = SearchResultAdapter(favorites) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(ParcelableKeys.ANIME_PARCELABLE, it)
            startActivity(intent)
        }
        binding.favorites.adapter = adapter
    }

}