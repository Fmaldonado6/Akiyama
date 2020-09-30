package com.fmaldonado.akiyama.ui.fragments.search

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.apiContent.AnimeModel
import com.fmaldonado.akiyama.data.models.apiContent.Status
import com.fmaldonado.akiyama.data.models.arguments.ActivitiesArguments
import com.fmaldonado.akiyama.ui.activities.animeActivity.AnimeActivity
import com.fmaldonado.akiyama.ui.recyclerItems.search.SearchItem
import com.google.gson.Gson
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.anime_display.errorGroup
import kotlinx.android.synthetic.main.anime_display.progressBar
import kotlinx.android.synthetic.main.search_fragment.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SearchFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    val factory by instance<SearchViewModelFactory>()

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(SearchViewModel::class.java)
        val searchBar = activity?.searchBar

        searchBar?.setOnKeyListener { view: View, i: Int, keyEvent: KeyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                search(searchBar.text.toString())

                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        initializeUi()

    }

    private fun initializeUi() {
        viewModel.status.observe(viewLifecycleOwner, Observer {
            changeStatus(it)
        })
        viewModel.searchResults.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                setUpRecycler(it.search)
                changeStatus(Status.Loaded.value)
            }
        })
    }


    private fun search(query: String) {
        viewModel.makeSearch(query)
    }

    private fun setUpRecycler(list: List<AnimeModel>) {

        val items = mutableListOf<SearchItem>()

        list.forEach {
            items.add(SearchItem(it, requireContext()))
        }

        val adapter = GroupAdapter<GroupieViewHolder>().apply {
            this.addAll(items)
        }

        adapter.setOnItemClickListener { item: Item<GroupieViewHolder>, view: View ->
            val item = item as SearchItem
            val intent = Intent(context, AnimeActivity::class.java)
            val gson = Gson()
            val json = gson.toJson(item.anime)
            intent.putExtra(ActivitiesArguments.Animes.value, json)
            intent.putExtra(ActivitiesArguments.GetInfo.value, true)
            startActivity(intent)
        }

        recycler.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

    }

    private fun changeStatus(status: Int) {

        when (status) {
            Status.Loading.value -> {
                progressBar.visibility = View.VISIBLE
                recycler.visibility = View.INVISIBLE
                errorGroup.visibility = View.INVISIBLE
                emptyGroup.visibility = View.INVISIBLE

            }
            Status.Loaded.value -> {
                progressBar.visibility = View.INVISIBLE
                recycler.visibility = View.VISIBLE
                errorGroup.visibility = View.INVISIBLE
                emptyGroup.visibility = View.INVISIBLE

            }
            Status.Error.value -> {
                errorGroup.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
                recycler.visibility = View.INVISIBLE
                emptyGroup.visibility = View.INVISIBLE

            }
            Status.Empty.value -> {
                errorGroup.visibility = View.INVISIBLE
                progressBar.visibility = View.INVISIBLE
                recycler.visibility = View.INVISIBLE
                emptyGroup.visibility = View.VISIBLE
            }
        }

    }

}