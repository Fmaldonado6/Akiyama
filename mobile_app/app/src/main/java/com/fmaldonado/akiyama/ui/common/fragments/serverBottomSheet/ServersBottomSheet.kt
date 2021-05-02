package com.fmaldonado.akiyama.ui.common.fragments.serverBottomSheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.models.content.Server
import com.fmaldonado.akiyama.databinding.ServerBottomSheetBinding
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.fmaldonado.akiyama.ui.common.ParcelableNames
import com.fmaldonado.akiyama.ui.common.Status
import com.fmaldonado.akiyama.ui.common.fragments.serverBottomSheet.adapters.ServerAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServersBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: ServerBottomSheetBinding
    private val viewModel: ServerBottomSheetViewModel by viewModels()
    private var episode: Episode? = null

    companion object {
        fun newInstance(episode: Episode): ServersBottomSheet {
            val sheet = ServersBottomSheet()
            val args = Bundle()
            args.putParcelable(ParcelableKeys.EPISODE_PARCELABLE, episode)
            sheet.arguments = args
            return sheet
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ServerBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        episode = arguments?.getParcelable(ParcelableKeys.EPISODE_PARCELABLE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val behavior = BottomSheetBehavior.from(view.parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED

        viewModel.servers.observe(this, {
            setupRecycler(it)
        })

        episode?.let {
            Log.d("Count",(it.servers == null).toString())

            if (it.servers == null)
                viewModel.getEpisodeServers(it.id)
            else
                viewModel.setServers(it.servers)
        }

        viewModel.currentStatus.observe(this, {
            binding.currentStatus = it
        })
    }

    private fun setupRecycler(servers: List<Server>) {
        val items = mutableListOf<ServerAdapter>()
        servers.forEach {
            items.add(ServerAdapter(it))
        }

        val adapter = GroupieAdapter().apply { addAll(items) }

        adapter.setOnItemClickListener { item: Item<GroupieViewHolder>, view: View ->

        }

        binding.serversList.apply {
            layoutManager = GridLayoutManager(context, 2)
            this.adapter = adapter
        }
    }
}