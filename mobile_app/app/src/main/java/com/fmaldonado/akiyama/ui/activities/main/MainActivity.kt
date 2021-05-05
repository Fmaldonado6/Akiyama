package com.fmaldonado.akiyama.ui.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.databinding.ActivityMainBinding
import com.fmaldonado.akiyama.ui.common.UpdateStatus
import com.fmaldonado.akiyama.ui.common.fragments.downloadDialog.DownloadDialog
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
        val navController = navHost.navController
        binding.bottomNav.setupWithNavController(navController)
        viewModel.checkLatestVersion()

        viewModel.hasNewVersion.observe(this, {
            val manual = it.manualClick
            when (it.status) {
                UpdateStatus.Looking -> Snackbar.make(
                    binding.bottomNav,
                    getString(R.string.looking_for_update_text),
                    Snackbar.LENGTH_SHORT
                ).show()
                UpdateStatus.NewUpdate -> Snackbar.make(
                    binding.bottomNav,
                    getString(R.string.new_update_text),
                    Snackbar.LENGTH_SHORT
                ).setAction("Download") {
                    DownloadDialog().show(supportFragmentManager, "dialog")
                }.show()
                UpdateStatus.Updated ->
                    if (it.manualClick) Snackbar.make(
                        binding.bottomNav,
                        getString(R.string.updated_text),
                        Snackbar.LENGTH_SHORT
                    ).show()
                UpdateStatus.Error -> Snackbar.make(
                    binding.bottomNav,
                    getString(R.string.error_update_text),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })

    }

}