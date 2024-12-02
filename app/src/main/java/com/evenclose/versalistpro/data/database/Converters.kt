package com.evenclose.versalistpro.data.database

import androidx.room.TypeConverter
import java.time.Instant

object Converters {
    @TypeConverter
    @JvmStatic
    fun fromTimestamp(timestamp: Long?): Instant? = timestamp?.let { Instant.ofEpochMilli(it) }

    @TypeConverter
    @JvmStatic
    fun toTimestamp(instant: Instant?): Long? = instant?.toEpochMilli()
}