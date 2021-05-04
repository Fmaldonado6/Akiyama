package com.fmaldonado.akiyama.ui.common

enum class Status(value: Int) {
    Loading(0),
    Loaded(1),
    Empty(3),
    Error(4),
}

enum class FavoritesStatus(value: Int) {
    Added(0),
    Removed(1),
    Error(2)
}
