package com.example.data.roomDB.entities.newsItem

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneItem(item: NewsItemDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSomeItems(vararg nextItem: NewsItemDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemsList(items: List<NewsItemDB>)

    @Update
    suspend fun updateOneItem(item: NewsItemDB)

    @Update
    suspend fun updateSomeItems(vararg nextItem: NewsItemDB)

    @Update
    suspend fun updateAllItems(items: List<NewsItemDB>)

    @Delete
    suspend fun deleteOneItem(item: NewsItemDB)

    @Delete
    suspend fun deleteSomeItems(vararg nextItem: NewsItemDB)

    @Delete
    suspend fun deleteAllItems(items: List<NewsItemDB>)

    @Query("SELECT * FROM items ORDER BY createdAt DESC, itemId")
    fun getAllItems(): Flow<List<NewsItemDB>>

    @Query("SELECT * FROM items WHERE itemId = :neededId")
    fun getItemById(neededId: String): Flow<NewsItemDB>

}