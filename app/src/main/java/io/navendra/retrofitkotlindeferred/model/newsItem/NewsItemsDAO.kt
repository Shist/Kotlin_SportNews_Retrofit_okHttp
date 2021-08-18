package io.navendra.retrofitkotlindeferred.model.newsItem

import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete

interface NewsItemsDAO {

    @Insert
    fun insertOneItem(item: NewsItem)

    @Insert
    fun insertSomeItems(vararg nextItem: NewsItem)

    @Insert
    fun insertItemsList(items: List<NewsItem>)

    @Update
    fun updateOneItem(item: NewsItem)

    @Update
    fun updateSomeItems(vararg nextItem: NewsItem)

    @Update
    fun updateAllItems(items: List<NewsItem>)

    @Delete
    fun deleteOneItem(item: NewsItem)

    @Delete
    fun deleteSomeItems(vararg nextItem: NewsItem)

    @Delete
    fun deleteAllItems(items: List<NewsItem>)

    @Query("SELECT * FROM items")
    fun getAllItems(): List<NewsItem>

    @Query("SELECT * FROM items WHERE itemId = :neededId")
    fun getItemById(neededId: String): NewsItem

}