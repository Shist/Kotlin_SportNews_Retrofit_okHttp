package io.navendra.retrofitkotlindeferred.roomDB.entities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.ZoneId

class Converters {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? {
        val zoneId: ZoneId = ZoneId.systemDefault()
        return date?.atStartOfDay(zoneId)?.toEpochSecond()
    }
}
