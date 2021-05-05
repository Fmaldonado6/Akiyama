package com.fmaldonado.akiyama.ui.common.fragments.downloadDialog

import android.app.ActionBar
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.fmaldonado.akiyama.BuildConfig
import com.fmaldonado.akiyama.data.utils.ApkInstaller
import com.fmaldonado.akiyama.databinding.DownloadDialogFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DownloadDialog : DialogFragment() {
    private lateinit var binding: DownloadDialogFragmentBinding
    private val viewModel: DownloadDialogViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = MaterialAlertDialogBuilder(requireActivity())
        binding = DownloadDialogFragmentBinding.inflate(requireActivity().layoutInflater)
        builder.setView(binding.root)
        return builder.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("BUenas", "AAAAAAAAAA")
        viewModel.downloadLatest()
        viewModel.file.observe(viewLifecycleOwner, {
            this.dismiss()
            ApkInstaller.installApplication(requireContext(), it)
        })

    }

}