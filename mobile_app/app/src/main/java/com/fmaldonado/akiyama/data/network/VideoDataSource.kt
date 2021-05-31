package com.fmaldonado.akiyama.data.network

import android.util.Log
import com.fmaldonado.akiyama.data.models.video.FembedVideoResponse
import com.fmaldonado.akiyama.data.models.video.VideoResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.jsoup.Jsoup
import ru.gildor.coroutines.okhttp.await
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class VideoDataSource
@Inject
constructor(
    private val client: OkHttpClient,
    private val gson: Gson
) {
    private val goCdnUrl = "https://streamium.xyz/gocdn.php?v="
    private val fembedUrl = "https://www.fembed.com/api/source/"
    private val hdLabel = "720p"

    suspend fun fetchGocdnVideo(id: String): VideoResponse {

        val req = Request.Builder().url(goCdnUrl + id).build()
        val response = client.newCall(req).await()
        val body = withContext(Dispatchers.IO) {
            kotlin.runCatching { response.body()?.string() }.getOrThrow()
        }
        return gson.fromJson(body, VideoResponse::class.java)
    }

    suspend fun fetchFembedVideo(id: String): VideoResponse {
        val req =
            Request.Builder().url(fembedUrl + id)
                .post(RequestBody.create(null, byteArrayOf())).build()
        val response = client.newCall(req).await()
        val body = withContext(Dispatchers.IO) {
            kotlin.runCatching { response.body()?.string() }.getOrThrow()
        }
        val fembedVideoResponse = gson.fromJson(body, FembedVideoResponse::class.java)

        var videoResponse = VideoResponse(fembedVideoResponse.data.first().file)

        if (fembedVideoResponse.data.size == 1)
            return videoResponse

        fembedVideoResponse.data.forEach {
            if (it.label == hdLabel)
                videoResponse = VideoResponse(it.file)
        }

        return videoResponse
    }

    suspend fun fetchStapeVideo(url: String) {
        Log.d("Url", url)
        val req = Request.Builder().url("https://streamtape.com/e/VrGka6PrmLIKd6O").build()
        val response = client.newCall(req).await()
        val body = withContext(Dispatchers.IO) {
            kotlin.runCatching { response.body()?.string() }.getOrThrow()
        }
        val document = Jsoup.parse(body)
        val link = document.getElementById("videolink")
        Log.d("Link", link.html())
    }

}
