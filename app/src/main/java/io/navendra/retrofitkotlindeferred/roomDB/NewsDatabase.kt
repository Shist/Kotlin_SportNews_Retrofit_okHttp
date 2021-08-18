package io.navendra.retrofitkotlindeferred.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import io.navendra.retrofitkotlindeferred.model.newsItem.NewsItem
import io.navendra.retrofitkotlindeferred.model.newsItem.NewsItemsDAO

@Database(entities = [NewsItem::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun itemsDao(): NewsItemsDAO
}