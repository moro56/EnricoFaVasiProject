package com.emperor.hpproject.data.network.services

import com.emperor.hpproject.data.network.models.RemoteHPCharacter
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/api/characters")
    suspend fun loadCharacters(): Response<List<RemoteHPCharacter>>
}