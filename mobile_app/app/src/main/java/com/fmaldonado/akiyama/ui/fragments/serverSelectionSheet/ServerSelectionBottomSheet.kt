package com.fmaldonado.akiyama.ui.fragments.serverSelectionSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.models.content.Server
import com.fmaldonado.akiyama.databinding.ServerBottomSheetBinding
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.fmaldonado.akiyama.ui.fragments.serverSelectionSheet.adapters.ServerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServerSelectionBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: ServerBottomSheetBinding
    private lateinit var episode: Episode
    private val viewModel: ServerSelectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ServerBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        episode = arguments?.getParcelable(ParcelableKeys.EPISODE_PARCELABLE) ?: return dismiss()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getStatus().observe(this) {
            binding.currentStatus = it
        }

        viewModel.getServerData().observe(this) {
            setupServerList(it)
        }

        viewModel.getServers(episode)
    }

    private fun setupServerList(servers: List<Server>) {
        val adapter = ServerAdapter(servers) {

        }
        binding.serversList.layoutManager = GridLayoutManager(context, 2)
        binding.serversList.adapter = adapter
    }

    companion object {
        const val TAG = "ServerSelectionBottomSheet"

        fun newInstance(episode: Episode): ServerSelectionBottomSheet {
            val instance = ServerSelectionBottomSheet()
            val args = Bundle()
            args.putParcelable(ParcelableKeys.EPISODE_PARCELABLE, episode)
            instance.arguments = args
            return instance
        }
    }

}