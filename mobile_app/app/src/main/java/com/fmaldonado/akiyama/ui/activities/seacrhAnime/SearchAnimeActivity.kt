package com.fmaldonado.akiyama.ui.activities.seacrhAnime

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.databinding.ActivitySearchAnimeBinding
import com.fmaldonado.akiyama.ui.activities.detail.DetailActivity
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.fmaldonado.akiyama.ui.common.adapters.SearchResultAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SearchAnimeActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchAnimeBinding
    private val viewModel: SearchAnimeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchBar.requestFocus()

        viewModel.getSearchResults().observe(this) {
            setupSearchList(it)
        }

        viewModel.getStatus().observe(this) {
            binding.currentStatus = it
        }

        viewModel.getInitialSearch()

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.searchBar.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.makeSearch(v.text.toString().lowercase(Locale.ROOT))
                hideKeyBoard()
                true
            } else {
                false
            }
        }

    }

    private fun hideKeyBoard() {
        val im = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = currentFocus
        view?.let { im.hideSoftInputFromWindow(view.windowToken, 0) }
    }

    private fun setupSearchList(result: List<Anime>) {
        val adapter = SearchResultAdapter(result) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(ParcelableKeys.ANIME_PARCELABLE, it)
            startActivity(intent)
        }
        binding.searchResults.adapter = adapter
    }
}