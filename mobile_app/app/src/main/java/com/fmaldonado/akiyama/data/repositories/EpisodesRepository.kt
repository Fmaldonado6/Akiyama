package com.fmaldonado.akiyama.data.repositories

import com.fmaldonado.akiyama.data.models.content.Episode
import com.fmaldonado.akiyama.data.models.content.Server
import com.fmaldonado.akiyama.data.network.NetworkDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EpisodesRepository
@Inject
constructor(
    private val networkDataSource: NetworkDataSource
) {

    suspend fun getEpisodeServers(episode: Episode): List<Server> {
        return networkDataSource.getServers(episode.id)
    }

}