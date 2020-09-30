package com.fmaldonado.akiyama.data.network.exceptions

import okhttp3.ResponseBody
import java.io.IOException
import java.lang.Exception

open class AppError(val error: ResponseBody?) : IOException() {
}