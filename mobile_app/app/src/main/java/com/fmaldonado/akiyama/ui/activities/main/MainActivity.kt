package com.fmaldonado.akiyama.ui.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.databinding.ActivityMainBinding
import com.fmaldonado.akiyama.ui.common.UpdateStatus
import com.fmaldonado.akiyama.ui.fragments.downloadDialog.DownloadDialog
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

        val navController =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment

        binding.bottomNavigation.setupWithNavController(navController.navController)

        viewModel.checkLatestVersion()
        viewModel.hasNewVersion.observe(this) {
            val manual = it.manualClick
            when (it.status) {
                UpdateStatus.Looking -> showSnackBar(getString(R.string.looking_for_update_text))
                UpdateStatus.NewUpdate -> showSnackBar(
                    getString(R.string.new_update_text),
                    getString(R.string.download_update_text)
                ) {
                    DownloadDialog().show(supportFragmentManager, DownloadDialog.TAG)
                }
                UpdateStatus.Updated -> if (manual) showSnackBar(getString(R.string.updated_text))
                UpdateStatus.Error -> showSnackBar(getString(R.string.error_update_text))

            }
        }
    }

    private fun showSnackBar(
        text: String,
        actionText: String? = "Ok",
        action: (() -> Unit)? = null
    ) {
        val snackBar = Snackbar.make(
            binding.bottomNavigation,
            text,
            Snackbar.LENGTH_SHORT
        )

        if (action != null) snackBar.setAction(actionText) { action() }


        snackBar.show()
    }
}