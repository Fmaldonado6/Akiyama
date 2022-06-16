package com.fmaldonado.akiyama.ui.activities.watch

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.content.Server
import com.fmaldonado.akiyama.databinding.ActivityWatchBinding
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.fmaldonado.akiyama.ui.common.Status
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWatchBinding
    private val viewModel: WatchActivityViewModel by viewModels()

    private var server: Server? = null
    private var player: ExoPlayer? = null
    private var url: String? = null
    private var useWebView = false
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        server = intent?.extras?.getParcelable(ParcelableKeys.SERVER_PARCELABLE)

        getUrl()

        viewModel.getUrlValue().observe(this) {
            if (it.useWebView)
                initializeWebview(it.file)
            else if (player == null)
                initializePlayer()
            this.url = it.file
            this.useWebView = it.useWebView
            hideUI()
        }

        viewModel.getStatus().observe(this) { binding.videoStatus = it }

        binding.videoError.retry.setOnClickListener { getUrl() }

        hideUI()
    }

    private fun getUrl() {
        server?.let {
            viewModel.getVideoUrl(it)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER
        releasePlayer()

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initializeWebview(url: String) {
        viewModel.setStatus(Status.Loaded)
        binding.videoView.visibility = View.GONE
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.domStorageEnabled = true

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
        binding.webView.loadUrl(url)
        hideUI()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        hideUI()
    }

    override fun onResume() {
        super.onResume()
        hideUI()
        if ((SDK_INT < 24 || player == null) && !useWebView)
            initializePlayer();
    }

    private fun initializePlayer() {
        url?.let {
            binding.webView.visibility = View.GONE
            player = ExoPlayer.Builder(this).build()
            binding.videoView.player = player
            val item = MediaItem.fromUri(it)
            player!!.setMediaItem(item)
            player!!.playWhenReady = playWhenReady;
            player!!.seekTo(currentWindow, playbackPosition);
            player!!.prepare();
        }
    }

    private fun releasePlayer() {
        player?.let {
            playWhenReady = it.playWhenReady;
            playbackPosition = it.currentPosition;
            currentWindow = it.currentMediaItemIndex;
            it.release()
            player = null
        }
    }

    override fun onPause() {
        super.onPause()
        if (SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (SDK_INT >= 24) {
            releasePlayer()
        }
    }

    private fun hideUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.layout).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}