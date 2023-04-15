package com.emperor.hpproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.emperor.hpproject.data.local.models.LocalHPCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<LocalHPCharacter>>

    @Query("SELECT * FROM characters WHERE name LIKE :name")
    suspend fun getFilteredCharacters(name: String): List<LocalHPCharacter>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg characters: LocalHPCharacter)
}