package io.navendra.retrofitkotlindeferred.roomDB.entities

import io.navendra.retrofitkotlindeferred.model.NewsItem

class NewsItemsAdapterDB {

    fun fromJsonToRoomDB(item: NewsItem) : NewsItemsDB
    {
        return NewsItemsDB(item.id, item.body, item.featuredMedia, item.shortHeadline)
    }
    
}