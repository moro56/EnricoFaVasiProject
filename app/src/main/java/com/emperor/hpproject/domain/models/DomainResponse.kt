package com.emperor.hpproject.domain.models

/**
 * Sealed class for defining the result of a domain request
 */
sealed class DomainResponse<out T> {

    /**
     * Success case
     *
     * @param result result of the domain request
     */
    data class Success<T>(val result: T) : DomainResponse<T>()

    /**
     * Error case
     *
     * @param exception the domain request exception
     */
    data class Error(val exception: Exception) : DomainResponse<Nothing>()
}
