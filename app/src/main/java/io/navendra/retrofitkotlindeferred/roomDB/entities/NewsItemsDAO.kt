package io.navendra.retrofitkotlindeferred.roomDB.entities

import androidx.room.*

@Dao
interface NewsItemsDAO {

    @Insert
    fun insertOneItem(item: NewsItemsDB)

    @Insert
    fun insertSomeItems(vararg nextItem: NewsItemsDB)

    @Insert
    fun insertItemsList(items: List<NewsItemsDB>)

    @Update
    fun updateOneItem(item: NewsItemsDB)

    @Update
    fun updateSomeItems(vararg nextItem: NewsItemsDB)

    @Update
    fun updateAllItems(items: List<NewsItemsDB>)

    @Delete
    fun deleteOneItem(item: NewsItemsDB)

    @Delete
    fun deleteSomeItems(vararg nextItem: NewsItemsDB)

    @Delete
    fun deleteAllItems(items: List<NewsItemsDB>)

    @Query("SELECT * FROM items")
    fun getAllItems(): List<NewsItemsDB>

    @Query("SELECT * FROM items WHERE itemId = :neededId")
    fun getItemById(neededId: String): NewsItemsDB

}