package com.emperor.hpproject.domain

import com.emperor.hpproject.data.local.dao.CharacterDao
import com.emperor.hpproject.data.network.RestApi
import com.emperor.hpproject.domain.ext.toHPCharacter
import com.emperor.hpproject.domain.ext.toHPCharacterList
import com.emperor.hpproject.domain.ext.toLocalHPCharacterList
import com.emperor.hpproject.domain.ext.wrapInDomainResponse
import com.emperor.hpproject.domain.models.DomainResponse
import com.emperor.hpproject.domain.models.HPCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val restApi: RestApi,
    private val characterDao: CharacterDao
) : Repository {

    override suspend fun downloadCharacters(): DomainResponse<Unit> = wrapInDomainResponse {
        restApi.loadAllCharacters().also {
            characterDao.insertAll(*it.toLocalHPCharacterList().toTypedArray())
        }.let { }
    }

    override suspend fun observeAllCharacters(): Flow<List<HPCharacter>> =
        characterDao.getAllCharacters().map {
            it.toHPCharacterList()
        }.catch {
            emit(listOf())
        }

    override suspend fun filterCharacters(name: String): DomainResponse<List<HPCharacter>> =
        wrapInDomainResponse {
            characterDao.getFilteredCharacters("%$name%").toHPCharacterList()
        }

    override suspend fun getCharacter(id: String): DomainResponse<HPCharacter> =
        wrapInDomainResponse {
            characterDao.getCharacterById(id)?.toHPCharacter()
        }
}