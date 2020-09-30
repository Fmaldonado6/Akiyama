package com.fmaldonado.akiyama.data.network.exceptions

import okhttp3.ResponseBody

class BadInput(error: ResponseBody?) : AppError(error) {
}