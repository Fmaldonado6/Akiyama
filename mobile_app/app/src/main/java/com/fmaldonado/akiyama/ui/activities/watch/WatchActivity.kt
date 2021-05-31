package com.fmaldonado.akiyama.ui.activities.watch

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.fmaldonado.akiyama.data.models.content.Server
import com.fmaldonado.akiyama.databinding.ActivityWatchBinding
import com.fmaldonado.akiyama.ui.common.ParcelableKeys
import com.fmaldonado.akiyama.ui.common.Status
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchActivity : AppCompatActivity() {
    private val viewModel: WatchActivityViewModel by viewModels()
    private var server: Server? = null
    private lateinit var binding: ActivityWatchBinding
    private var player: SimpleExoPlayer? = null
    private var url: String? = null
    private var useWebView = false
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        server = intent?.extras?.getParcelable(ParcelableKeys.SERVER_PARCELABLE)

        getUrl()

        viewModel.urlValue.observe(this, {
            if (it.useWebView)
                initializeWebview(it.file)
            else if (player == null)
                initializePlayer()
            this.url = it.file
            this.useWebView = it.useWebView
            hideUI()
        })

        viewModel.videoStatus.observe(this, { binding.videoStatus = it })

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

    private fun initializeWebview(url: String) {
        viewModel.setStatus(Status.Loaded)
        binding.videoView.visibility = View.GONE
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
            player = SimpleExoPlayer.Builder(this).build()
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
            currentWindow = it.currentWindowIndex;
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

        val immersive = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

        window.decorView.systemUiVisibility = immersive

        binding.videoView.systemUiVisibility = immersive

        binding.webView.systemUiVisibility = immersive
    }


}