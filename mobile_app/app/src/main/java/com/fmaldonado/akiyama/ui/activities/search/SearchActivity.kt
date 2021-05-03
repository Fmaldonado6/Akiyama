package com.fmaldonado.akiyama.ui.activities.search

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Anime
import com.fmaldonado.akiyama.databinding.ActivitySearchBinding
import com.fmaldonado.akiyama.ui.activities.animeDetail.AnimeDetailActivity
import com.fmaldonado.akiyama.ui.activities.search.adapters.SearchAdapter
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.fmaldonado.akiyama.ui.common.adapters.AnimeAdapter
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val viewModel: SearchActivityViewModel by viewModels()
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchBar.requestFocus()
        binding.searchBar.setText(viewModel.currentSearchQuery)
        viewModel.initView()
        viewModel.currentStatus.observe(this, { binding.currentStatus = it })
        viewModel.searchResults.observe(this, {
            setupRecycler(it)
        })

        binding.searchBar.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                viewModel.makeSearch(v.text.toString())
                true
            } else {
                false
            }
        }
    }


    private fun setupRecycler(searchResults: List<Anime>) {
        val items = mutableListOf<SearchAdapter>()
        searchResults.forEach {
            items.add(SearchAdapter(it))
        }

        val adapter = GroupieAdapter().apply { addAll(items) }

        adapter.setOnItemClickListener { item: Item<GroupieViewHolder>, view: View ->
            val animeItem = item as SearchAdapter
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