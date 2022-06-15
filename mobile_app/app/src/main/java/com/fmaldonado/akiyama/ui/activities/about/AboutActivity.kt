package com.fmaldonado.akiyama.ui.activities.about

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.MenuItem
import com.fmaldonado.akiyama.R
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.fmaldonado.akiyama.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.iconLink.movementMethod = LinkMovementMethod.getInstance()

        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId != R.id.openSource) return@setOnMenuItemClickListener false
            OssLicensesMenuActivity.setActivityTitle(getString(R.string.open_source_licenses))
            startActivity(Intent(this, OssLicensesMenuActivity::class.java))
            return@setOnMenuItemClickListener true
        }
    }


}