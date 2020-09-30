package com.fmaldonado.akiyama.data.network.exceptions

import okhttp3.ResponseBody

class EmptySearch(error: ResponseBody?) : AppError(error) {
}