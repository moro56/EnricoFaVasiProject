package com.emperor.hpproject.data.local.converters

import androidx.room.TypeConverter

class StringListConverter {

    @TypeConverter
    fun fromListStringToString(stringList: List<String>): String = stringList.joinToString("||")

    @TypeConverter
    fun toListStringFromString(stringList: String): List<String> =
        if (stringList.isBlank()) listOf() else stringList.split("||")
}