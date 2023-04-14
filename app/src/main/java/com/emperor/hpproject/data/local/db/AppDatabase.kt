package com.emperor.hpproject.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.emperor.hpproject.data.local.converters.StringListConverter
import com.emperor.hpproject.data.local.dao.CharacterDao
import com.emperor.hpproject.data.local.models.LocalHPCharacter

@TypeConverters(StringListConverter::class)
@Database(entities = [LocalHPCharacter::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}