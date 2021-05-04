package com.fmaldonado.akiyama.data.persistence.converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters

object StringListConverter {

    @TypeConverter
    fun fromString(string: String): List<String> {
        return string.split(",")
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(separator = ",")
    }
}