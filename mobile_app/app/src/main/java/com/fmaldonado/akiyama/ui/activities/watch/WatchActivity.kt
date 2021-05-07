package com.fmaldonado.akiyama.ui.activities.watch

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.fmaldonado.akiyama.data.models.content.Server
import com.fmaldonado.akiyama.databinding.ActivityWatchBinding
import com.fmaldonado.akiyama.ui.common.ParcelableKeys

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
        hideUI()
    }

    override fun onDestroy() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER
        super.onDestroy()

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        hideUI()
    }

    override fun onResume() {
        super.onResume()
        hideUI()
    }

    private fun hideUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }

}