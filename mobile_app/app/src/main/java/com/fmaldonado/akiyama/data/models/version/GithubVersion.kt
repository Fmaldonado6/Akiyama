package com.fmaldonado.akiyama.data.models.version

data class GithubVersion(
    val tag_name: String,
    val name: String,
    val prerelease: Boolean,
    val assets: List<GithubAsset>
)

data class GithubAsset(
    val name: String,
    val browser_download_url: String
)
