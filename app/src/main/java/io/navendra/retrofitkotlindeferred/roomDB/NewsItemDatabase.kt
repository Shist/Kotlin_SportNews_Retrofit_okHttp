package io.navendra.retrofitkotlindeferred.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.navendra.retrofitkotlindeferred.roomDB.entities.Converters
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemTableImpl
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemDAO
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails.NewsItemDetailsDAO
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails.NewsItemDetailsTableImpl

@Database(entities = [NewsItemTableImpl::class, NewsItemDetailsTableImpl::class], version = 3)
@TypeConverters(Converters::class)
abstract class NewsItemDatabase : RoomDatabase() {
    abstract fun itemsDao(): NewsItemDAO
    abstract fun itemsDetailsDao(): NewsItemDetailsDAO
}