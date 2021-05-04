package com.fmaldonado.akiyama.ui.activities.about

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.databinding.ActivityAboutBinding
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        OssLicensesMenuActivity.setActivityTitle(getString(R.string.open_source_licenses))
        binding.openSourceButton.setOnClickListener {
            startActivity(Intent(this, OssLicensesMenuActivity::class.java))
        }
        binding.backButton.setOnClickListener { finish() }
        binding.iconLink.movementMethod = LinkMovementMethod.getInstance()
    }
}