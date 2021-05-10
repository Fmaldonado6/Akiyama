package com.fmaldonado.akiyama.data.repositories.videos

import android.util.Log
import com.fmaldonado.akiyama.data.models.content.Server
import com.fmaldonado.akiyama.data.models.video.ServerTypes
import com.fmaldonado.akiyama.data.models.video.VideoResponse
import com.fmaldonado.akiyama.data.network.VideoDataSource
import javax.inject.Inject

class VideoRepository
@Inject
constructor(
    private val videoDataSource: VideoDataSource
) {

    suspend fun getVideoUrl(server: Server): VideoResponse {
        return when (server.server) {
            ServerTypes.GoCdn.value -> getGoCdnVideo(server.code)
            ServerTypes.Fembed.value -> getFembedVideo(server.code)
            else -> VideoResponse(server.code, true)
        }
    }

    suspend fun getGoCdnVideo(url: String): VideoResponse {
        val value = url.split("#").last()
        return videoDataSource.fetchGocdnVideo(value)
    }

    suspend fun getFembedVideo(url: String): VideoResponse {
        val value = url.split("/").last()
        return videoDataSource.fetchFembedVideo(value)
    }

    suspend fun getStapeVideo(url: String): VideoResponse {
        videoDataSource.fetchStapeVideo(url)
        return VideoResponse("")
    }

}