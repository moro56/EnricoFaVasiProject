package com.emperor.hpproject.data.network.services

import com.emperor.hpproject.data.network.models.HPCharacter
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/characters")
    suspend fun loadCharacters(): Response<List<HPCharacter>>
}