package io.navendra.retrofitkotlindeferred.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.navendra.retrofitkotlindeferred.roomDB.entities.Converters
import io.navendra.retrofitkotlindeferred.roomDB.entities.NewsItemDB
import io.navendra.retrofitkotlindeferred.roomDB.entities.NewsItemsDAO

@Database(entities = [NewsItemDB::class], version = 1)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun itemsDao(): NewsItemsDAO
}