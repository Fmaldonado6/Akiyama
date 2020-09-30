package com.fmaldonado.akiyama.data.network.exceptions

import okhttp3.ResponseBody
import java.lang.Exception

class NotFoundError(error: ResponseBody?) : AppError(error) {
}