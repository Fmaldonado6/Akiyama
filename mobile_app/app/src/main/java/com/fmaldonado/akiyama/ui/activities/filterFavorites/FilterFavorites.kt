package com.fmaldonado.akiyama.ui.activities.filterFavorites

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.databinding.ActivityFilterFavoritesBinding
import com.fmaldonado.akiyama.ui.activities.detail.DetailActivity
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.fmaldonado.akiyama.ui.common.adapters.SearchResultAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterFavorites : AppCompatActivity() {

    private lateinit var binding: ActivityFilterFavoritesBinding
    private val viewModel: FilterFavoritesViewModel by viewModels()
    private var adapter: SearchResultAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.searchBar.requestFocus()
        setupRecycler()

        viewModel.getStatus().observe(this) {
            binding.currentStatus = it
        }

        binding.searchBar.addTextChangedListener {
            Log.d("QUery", it.toString())
            viewModel.filterFavorites(it.toString())
            setupRecycler()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecycler() {

        if (adapter != null) {
            adapter!!.updateList(viewModel.getFavorites())
            return
        }

        adapter = SearchResultAdapter(viewModel.getFavorites()) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(ParcelableKeys.ANIME_PARCELABLE, it)
            startActivity(intent)
        }
        binding.searchResults.adapter = adapter
    }
}