package com.fmaldonado.akiyama.data.network.interceptor

import com.fmaldonado.akiyama.data.network.exceptions.AppError
import com.fmaldonado.akiyama.data.network.exceptions.BadInput
import com.fmaldonado.akiyama.data.network.exceptions.EmptySearch
import com.fmaldonado.akiyama.data.network.exceptions.NotFoundError
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptorImpl :
    NetworkInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.isSuccessful)
            return response

        if (response.code() == 400)
            throw BadInput(response.body())
        if (response.code() == 404)
            throw NotFoundError(response.body())

        if (response.code() == 500)
            throw  EmptySearch(response.body())
        throw  AppError(response.body())

    }
}