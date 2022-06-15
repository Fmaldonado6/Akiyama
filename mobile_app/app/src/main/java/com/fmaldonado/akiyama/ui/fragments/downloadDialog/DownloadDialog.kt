package com.fmaldonado.akiyama.ui.fragments.downloadDialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.fmaldonado.akiyama.data.utils.ApkInstaller
import com.fmaldonado.akiyama.databinding.FragmentDownloadDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DownloadDialog : DialogFragment() {

    private lateinit var binding: FragmentDownloadDialogBinding
    private val viewModel: DownloadDialogViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = MaterialAlertDialogBuilder(requireContext())
        binding = FragmentDownloadDialogBinding.inflate(requireActivity().layoutInflater)
        builder.setView(binding.root)
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
        viewModel.downloadLatest()
        binding.button.setOnClickListener { dismiss() }
        viewModel.file.observe(viewLifecycleOwner) {
            this.dismiss()
            ApkInstaller.installApplication(requireContext(), it)
        }
    }

    companion object {
        const val TAG = "DownloadDialog"
    }
}