package com.fmaldonado.akiyama.data.models.apiContent

data class EpisodeModel(
    val id:String,
    val title:String,
    val image:String,
    val episode:Float,
    val servers:List<ServerModel>
) {
}