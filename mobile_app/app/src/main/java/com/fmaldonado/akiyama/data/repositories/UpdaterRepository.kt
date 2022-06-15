package com.fmaldonado.akiyama.data.repositories


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.fmaldonado.akiyama.AkiyamaApplication
import com.fmaldonado.akiyama.BuildConfig
import com.fmaldonado.akiyama.data.models.version.GithubVersion
import com.fmaldonado.akiyama.data.network.LatestVersionDataSource
import com.fmaldonado.akiyama.ui.common.UpdateEvents
import com.fmaldonado.akiyama.ui.common.UpdateStatus
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.gildor.coroutines.okhttp.await
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdaterRepository
@Inject
constructor(
    private val latestVersionDataSource: LatestVersionDataSource,
    private val okHttpClient: OkHttpClient,
    private val application: AkiyamaApplication
) {

    val hasNewVersion = MutableLiveData<UpdateEvents>()
    val newVersionUri = MutableLiveData<File>()
    private var version: GithubVersion? = null

    suspend fun lookForNewVersion(manualClick: Boolean) {
        if (manualClick) {
            hasNewVersion.postValue(UpdateEvents(UpdateStatus.Looking, manualClick))
            delay(1000)
        }
        version = latestVersionDataSource.getLatestRelease()
        version?.let {
            if (it.prerelease)
                return
            val status = if (it.tag_name != BuildConfig.VERSION_NAME )
                UpdateStatus.NewUpdate
            else
                UpdateStatus.Updated
            hasNewVersion.postValue(
                UpdateEvents(
                    status,
                    manualClick
                )
            )
        }
    }

    suspend fun downloadLatest() {
        version?.let {
            val asset = it.assets.firstOrNull() ?: throw Exception()
            val req = Request.Builder().url(asset.browser_download_url).build()
            val res = okHttpClient.newCall(req).await()
            val bytes = res.body?.byteStream() ?: throw Exception()
            val file = File(application.externalCacheDir, "update.apk")
            bytes.copyTo(file.outputStream())
            newVersionUri.postValue(file)
        }
    }

}