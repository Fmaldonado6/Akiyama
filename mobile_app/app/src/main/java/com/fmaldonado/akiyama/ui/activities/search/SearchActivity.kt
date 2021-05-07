package com.fmaldonado.akiyama.ui.activities.search

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.databinding.ActivitySearchBinding
import com.fmaldonado.akiyama.ui.activities.animeDetail.AnimeDetailActivity
import com.fmaldonado.akiyama.ui.common.adapters.AnimeListAdapter
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.fmaldonado.akiyama.ui.common.Status
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val viewModel: SearchActivityViewModel by viewModels()
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchBar.requestFocus()

        viewModel.currentStatus.observe(this, { binding.currentStatus = it })
        viewModel.searchResults.observe(this, {
            when (it.isEmpty()) {
                true -> viewModel.currentStatus.value = Status.Empty
                else -> {
                    viewModel.currentStatus.value = Status.Loaded
                    setupRecycler(it)
                }
            }
        })

        binding.backButton.setOnClickListener { finish() }

        binding.searchBar.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.makeSearch(v.text.toString().toLowerCase(Locale.ROOT))
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


    private fun setupRecycler(searchResults: List<Anime>) {
        val items = mutableListOf<AnimeListAdapter>()
        searchResults.forEach {
            items.add(AnimeListAdapter(it))
        }

        val adapter = GroupieAdapter().apply { addAll(items) }

        adapter.setOnItemClickListener { item: Item<GroupieViewHolder>, view: View ->
            val animeItem = item as AnimeListAdapter
            val intent = Intent(this, AnimeDetailActivity::class.java)
            intent.putExtra(ParcelableKeys.ANIME_PARCELABLE, animeItem.anime)
            startActivity(intent)
        }

        binding.searchResults.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
    }
}