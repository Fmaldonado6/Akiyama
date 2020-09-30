package com.fmaldonado.akiyama.ui.fragments.dialogs.bottomSheet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.apiContent.ServerModel
import com.fmaldonado.akiyama.data.models.apiContent.Status
import com.fmaldonado.akiyama.data.models.arguments.ActivitiesArguments
import com.fmaldonado.akiyama.ui.activities.watchActivity.WatchActivity
import com.fmaldonado.akiyama.ui.fragments.animeDisplay.AnimeDisplayViewModel
import com.fmaldonado.akiyama.ui.fragments.animeDisplay.AnimeDisplayViewModelFactory
import com.fmaldonado.akiyama.ui.recyclerItems.servers.ServerItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.server_sheet.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ServersBottomSheet : BottomSheetDialogFragment(), KodeinAware {
    override val kodein by closestKodein()

    var episodeId: String = ""

    var servers: List<ServerModel>? = null

    private val factory by instance<ServerBottomSheetViewModelFactory>()

    lateinit var viewModel: ServerBottomSheetViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.server_sheet, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, factory).get(ServerBottomSheetViewModel::class.java)

        episodeId = arguments?.getString(ActivitiesArguments.EpisodeId.value, "").toString()

        val serverString = arguments?.getString(ActivitiesArguments.Servers.value, "").toString()

        if (serverString.isEmpty())
            getServers()
        else {

            val gson = Gson()
            val type = object : TypeToken<List<ServerModel>>() {}.type
            servers = gson.fromJson(serverString, type)
            showServers()

        }


    }

    private fun getServers() {
        viewModel.status.observe(viewLifecycleOwner, Observer {
            changeStatus(it)
        })

        viewModel.getServers(episodeId)

        viewModel.servers.observe(viewLifecycleOwner, Observer {
            servers = it.servers
            showServers()
        })
    }

    private fun showServers() {

        val items = mutableListOf<ServerItem>()
        servers?.forEach {
            items.add(ServerItem(it))
        }

        val adapter = GroupAdapter<GroupieViewHolder>().apply {
            this.addAll(items)
        }

        adapter.setOnItemClickListener { item: Item<GroupieViewHolder>, view: View ->
            val serverItem = item as ServerItem
            val intent = Intent(activity, WatchActivity::class.java)
            intent.putExtra(ActivitiesArguments.ServerUrl.value, serverItem.server.url)
            activity?.startActivity(intent)

        }
        serverList.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }


        retryButton.setOnClickListener {
            viewModel.getServers(episodeId)
        }


    }

    private fun changeStatus(status: Int) {

        when (status) {
            Status.Loading.value -> {
                progressBar.visibility = View.VISIBLE
                serverList.visibility = View.INVISIBLE
                errorGroup.visibility = View.INVISIBLE

            }
            Status.Loaded.value -> {
                progressBar.visibility = View.INVISIBLE
                serverList.visibility = View.VISIBLE
                errorGroup.visibility = View.INVISIBLE
            }
            Status.Error.value -> {
                errorGroup.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
                serverList.visibility = View.INVISIBLE
            }
        }

    }
}