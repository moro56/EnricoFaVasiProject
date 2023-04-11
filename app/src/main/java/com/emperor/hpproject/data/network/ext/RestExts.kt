package com.emperor.hpproject.data.network.ext

import retrofit2.Response

/**
 * Wrapper for creating a model from a Rest api
 *
 * @param request the Rest api request
 */
suspend fun <T : Response<K>, K> wrapInApiResult(request: suspend () -> T): K? {
    val response = request.invoke()
    if (response.isSuccessful) {
        return response.body()
    } else {
        throw Exception(response.message())
    }
}