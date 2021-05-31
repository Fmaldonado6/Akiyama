package com.fmaldonado.akiyama.data.models.video

data class VideoResponse(
    val file: String = "",
    val useWebView:Boolean = false
)

data class FembedVideoResponse(
    val data: List<FembedData>
)

data class FembedData(
    val label: String,
    val file: String
)

enum class ServerTypes(val value: String) {
    GoCdn("gocdn"),
    Fembed("fembed"),
    Mega("Mega"),
    Stape("stape"),
    Okru("okru"),
    Netu("netu")
}