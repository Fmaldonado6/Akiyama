package com.fmaldonado.akiyama.data.network

import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptorImpl : NetworkInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
    }
}