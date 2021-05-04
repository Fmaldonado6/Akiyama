package com.fmaldonado.akiyama.ui.activities.watch

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Server
import com.fmaldonado.akiyama.databinding.ActivityWatchBinding
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.AndroidEntryPoint

class WatchActivity : AppCompatActivity() {

    private var server: Server? = null
    private lateinit var binding: ActivityWatchBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        server = intent?.extras?.getParcelable(ParcelableKeys.SERVER_PARCELABLE)
        server?.let {
            binding.webView.settings.javaScriptEnabled = true
            binding.webView.settings.allowFileAccess = false
            binding.webView.settings.javaScriptCanOpenWindowsAutomatically = false
            binding.webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    url: String
                ): Boolean {
                    return true
                }
            }
            binding.webView.loadUrl(it.code)
        }
    }

    override fun onDestroy() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER
        super.onDestroy()

    }
}