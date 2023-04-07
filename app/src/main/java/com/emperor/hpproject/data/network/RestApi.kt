package com.emperor.hpproject.data.network

import com.emperor.hpproject.data.network.models.HPCharacter

interface RestApi {

    /**
     * Load all the HP characters
     *
     * @return list of HP characters
     */
    suspend fun loadAllCharacters(): List<HPCharacter>
}