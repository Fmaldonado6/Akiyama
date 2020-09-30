package com.fmaldonado.akiyama.data.models.responses

import com.fmaldonado.akiyama.data.models.apiContent.ServerModel

data class ServerResponse(
    val servers: List<ServerModel>
) {
}