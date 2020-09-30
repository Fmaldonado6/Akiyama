package com.fmaldonado.akiyama.data.models.apiContent

enum class Status(val value: Int) {
    Loading(0),
    Loaded(1),
    Error(2),
    Empty(3)
}