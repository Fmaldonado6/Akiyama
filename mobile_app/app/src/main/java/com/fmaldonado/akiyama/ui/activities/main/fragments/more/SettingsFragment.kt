package com.fmaldonado.akiyama.ui.activities.main.fragments.more

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.fmaldonado.akiyama.BuildConfig
import com.fmaldonado.akiyama.R
import com.fmaldonado.akiyama.ui.activities.about.AboutActivity
import com.fmaldonado.akiyama.ui.activities.main.MainActivityViewModel
import com.fmaldonado.akiyama.ui.activities.watch.WatchActivity
import com.fmaldonado.akiyama.ui.common.PreferenceKeys

class SettingsFragment : PreferenceFragmentCompat() {
    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)

        val versionPref = findPreference<Preference>(PreferenceKeys.VERSION)

        versionPref?.let {
            it.summary = BuildConfig.VERSION_NAME
        }
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {

        when (preference.key) {
            PreferenceKeys.ABOUT -> startIntent(AboutActivity::class.java)
            PreferenceKeys.TWITTER -> startURLIntent("https://twitter.com/Fmaldonado4202")
            PreferenceKeys.SOURCE_CODE -> startURLIntent("https://github.com/Fmaldonado6/Akiyama")
            PreferenceKeys.CHECK_UPDATES -> checkUpdates()
            else -> Log.d(this.tag, "Unregistered preference")
        }

        return super.onPreferenceTreeClick(preference)
    }


    private fun checkUpdates() {
        viewModel.checkLatestVersion(true)
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