package com.example.data.roomDB.entities.newsItemDetails

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsItemDetailsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneItemDetails(itemDetails: NewsItemDetailsDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSomeItemsDetails(vararg nextItemDetails: NewsItemDetailsDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemsDetailsList(itemsDetails: List<NewsItemDetailsDB>)

    @Update
    suspend fun updateOneItemDetails(itemDetails: NewsItemDetailsDB)

    @Update
    suspend fun updateSomeItemsDetails(vararg nextItem: NewsItemDetailsDB)

    @Update
    suspend fun updateAllItemsDetails(itemsDetails: List<NewsItemDetailsDB>)

    @Delete
    suspend fun deleteOneItemDetails(itemDetails: NewsItemDetailsDB)

    @Delete
    suspend fun deleteSomeItemsDetails(vararg nextItemDetails: NewsItemDetailsDB)

    @Delete
    suspend fun deleteAllItemsDetails(itemsDetails: List<NewsItemDetailsDB>)

    @Query("SELECT * FROM itemsWithDetails ORDER BY createdAt DESC, itemId")
    fun getAllItemsDetails(): Flow<List<NewsItemDetailsDB>>

    @Query("SELECT * FROM itemsWithDetails WHERE itemId = :neededId")
    fun getItemDetailsById(neededId: String): Flow<NewsItemDetailsDB>

}