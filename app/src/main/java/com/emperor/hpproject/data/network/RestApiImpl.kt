package com.emperor.hpproject.data.network

import com.emperor.hpproject.data.network.ext.wrapInApiResult
import com.emperor.hpproject.data.network.models.HPCharacter
import com.emperor.hpproject.data.network.services.ApiService
import javax.inject.Singleton

@Singleton
class RestApiImpl(private val apiService: ApiService) : RestApi {

    override suspend fun loadAllCharacters(): List<HPCharacter> =
        wrapInApiResult { apiService.loadCharacters() } ?: listOf()
}