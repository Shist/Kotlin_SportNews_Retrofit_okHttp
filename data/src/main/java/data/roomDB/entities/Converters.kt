package data.roomDB.entities

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.ZoneId

class Converters {
    @TypeConverter
    fun fromTime(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }

    @TypeConverter
    fun dateToTime(date: LocalDate?): Long? {
        val zoneId: ZoneId = ZoneId.systemDefault()
        return date?.atStartOfDay(zoneId)?.toEpochSecond()
    }
}
