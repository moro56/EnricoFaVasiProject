package com.emperor.hpproject.domain

import com.emperor.hpproject.domain.models.DomainResponse
import com.emperor.hpproject.domain.models.HPCharacter

interface Repository {

    /**
     * Load all the HP characters
     *
     * @return list of HP characters
     */
    suspend fun loadAllCharacters(): DomainResponse<List<HPCharacter>>
}