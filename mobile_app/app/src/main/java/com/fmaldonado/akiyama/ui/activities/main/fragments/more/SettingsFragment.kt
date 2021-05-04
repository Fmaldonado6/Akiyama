package com.fmaldonado.akiyama.ui.activities.main.fragments.more

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.ui.activities.about.AboutActivity

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {

        preference?.let {

            when (it.key) {
                PreferenceKeys.ABOUT -> startIntent(AboutActivity::class.java)
                PreferenceKeys.TWITTER -> startURLIntent("https://twitter.com/Fmaldonado4202")
                PreferenceKeys.SOURCE_CODE -> startURLIntent("https://github.com/Fmaldonado6/Akiyama")
                else -> Log.d(this.tag, "Unregistered preference")
            }

        }

        return super.onPreferenceTreeClick(preference)
    }

    private fun startURLIntent(url: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
        startActivity(intent)
    }

    private fun <T> startIntent(activity: Class<T>) {
        val intent = Intent(context, activity)
        startActivity(intent)
    }

}