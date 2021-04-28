package com.fmaldonado.akiyama.data.network.exceptions

import java.io.IOException
import okhttp3.ResponseBody

open class AppError(val error: ResponseBody?) : IOException()

class BadInput(error: ResponseBody?) : AppError(error)

class EmptySearch(error: ResponseBody?) : AppError(error)

class NotFoundError(error: ResponseBody?) : AppError(error)