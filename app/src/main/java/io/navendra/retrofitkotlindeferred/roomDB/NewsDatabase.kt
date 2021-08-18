package io.navendra.retrofitkotlindeferred.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import io.navendra.retrofitkotlindeferred.model.newsItem.NewsItemsDB
import io.navendra.retrofitkotlindeferred.roomDB.entities.NewsItemsDAO

@Database(entities = [NewsItemsDB::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun itemsDao(): NewsItemsDAO
}