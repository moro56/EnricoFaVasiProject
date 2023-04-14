package com.emperor.hpproject.domain

import com.emperor.hpproject.domain.models.DomainResponse
import com.emperor.hpproject.domain.models.HPCharacter
import kotlinx.coroutines.flow.Flow

interface Repository {

    /**
     * Download the character list from the server
     */
    suspend fun downloadCharacters(): DomainResponse<Unit>

    /**
     * Observe all the HP characters
     *
     * @return list of HP characters
     */
    suspend fun observeAllCharacters(): Flow<List<HPCharacter>>
}