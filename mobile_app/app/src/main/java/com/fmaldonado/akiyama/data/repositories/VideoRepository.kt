package com.fmaldonado.akiyama.data.repositories

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
        return when (server.serverCode) {
            ServerTypes.GoCdn.value -> getGoCdnVideo(server.url)
            ServerTypes.Fembed.value -> getFembedVideo(server.url)
            else -> VideoResponse(server.url, true)
        }
    }

    private suspend fun getGoCdnVideo(url: String): VideoResponse {
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