package com.emperor.hpproject.domain

import com.emperor.hpproject.data.network.RestApi
import com.emperor.hpproject.domain.ext.toHPCharacterList
import com.emperor.hpproject.domain.ext.wrapInDomainResponse
import com.emperor.hpproject.domain.models.DomainResponse
import com.emperor.hpproject.domain.models.HPCharacter
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val restApi: RestApi) : Repository {

    override suspend fun loadAllCharacters(): DomainResponse<List<HPCharacter>> =
        wrapInDomainResponse {
            restApi.loadAllCharacters().toHPCharacterList()
        }
}