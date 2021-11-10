package com.example.data.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.roomDB.entities.Converters
import com.example.data.roomDB.entities.newsItem.NewsItemDB
import com.example.data.roomDB.entities.newsItem.NewsItemDAO
import com.example.data.roomDB.entities.newsItemDetails.NewsItemDetailsDAO
import com.example.data.roomDB.entities.newsItemDetails.NewsItemDetailsDB

@Database(entities = [NewsItemDB::class, NewsItemDetailsDB::class], version = 3)
@TypeConverters(Converters::class)
abstract class NewsItemDatabase : RoomDatabase() {
    abstract fun itemsDao(): NewsItemDAO
    abstract fun itemsDetailsDao(): NewsItemDetailsDAO
}