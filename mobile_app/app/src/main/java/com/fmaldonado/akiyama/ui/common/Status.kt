package com.fmaldonado.akiyama.ui.common

enum class Status(value: Int) {
    Loading(0),
    Loaded(1),
    Empty(3),
    Error(4),
}

data class UpdateEvents(
    val status: UpdateStatus,
    val manualClick: Boolean
)


enum class UpdateStatus(value: Int) {
    Looking(0),
    NewUpdate(1),
    Updated(2),
    Error(3)
}

enum class FavoritesStatus(value: Int) {
    Added(0),
    Removed(1),
    Error(2)
}
