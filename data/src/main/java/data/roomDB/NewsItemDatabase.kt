package data.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import data.roomDB.entities.Converters
import data.roomDB.entities.newsItem.NewsItemDB
import data.roomDB.entities.newsItem.NewsItemDAO
import data.roomDB.entities.newsItemDetails.NewsItemDetailsDAO
import data.roomDB.entities.newsItemDetails.NewsItemDetailsDB

@Database(entities = [NewsItemDB::class, NewsItemDetailsDB::class], version = 3)
@TypeConverters(Converters::class)
abstract class NewsItemDatabase : RoomDatabase() {
    abstract fun itemsDao(): NewsItemDAO
    abstract fun itemsDetailsDao(): NewsItemDetailsDAO
}