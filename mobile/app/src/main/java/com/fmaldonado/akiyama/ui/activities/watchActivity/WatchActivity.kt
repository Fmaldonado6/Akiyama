package com.fmaldonado.akiyama.ui.activities.watchActivity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.data.models.arguments.ActivitiesArguments
import kotlinx.android.synthetic.main.activity_watch.*


class WatchActivity : AppCompatActivity() {

    private var url: String? = ""

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    @SuppressLint("ClickableViewAccessibility", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_watch)

        hideNav()

        url = intent.getStringExtra(ActivitiesArguments.ServerUrl.value)

        if (url != null || url != "") {
            webView.settings.javaScriptEnabled = true
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    url: String
                ): Boolean {
                    return true
                }
            }
            webView.loadUrl(url)

        }

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun hideNav() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onResume() {
        super.onResume()
        hideNav()
    }


}