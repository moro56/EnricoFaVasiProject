package com.emperor.hpproject.domain.ext

import com.emperor.hpproject.domain.models.DomainResponse

/**
 * Wrapper for creating an [DomainResponse] of a domain request
 *
 * @param request the Api request
 */
suspend fun <T> wrapInDomainResponse(request: suspend () -> T?): DomainResponse<T> = try {
    val response = request.invoke()
    if (response != null) {
        DomainResponse.Success(response)
    } else {
        DomainResponse.Error(Exception())
    }
} catch (e: Exception) {
    DomainResponse.Error(e)
}