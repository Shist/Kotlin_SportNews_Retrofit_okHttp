package io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsItemDetailsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneItemDetails(itemDetails: NewsItemDetailsTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSomeItemsDetails(vararg nextItemDetails: NewsItemDetailsTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemsDetailsList(itemsDetails: List<NewsItemDetailsTable>)

    @Update
    suspend fun updateOneItemDetails(itemDetails: NewsItemDetailsTable)

    @Update
    suspend fun updateSomeItemsDetails(vararg nextItem: NewsItemDetailsTable)

    @Update
    suspend fun updateAllItemsDetails(itemsDetails: List<NewsItemDetailsTable>)

    @Delete
    suspend fun deleteOneItemDetails(itemDetails: NewsItemDetailsTable)

    @Delete
    suspend fun deleteSomeItemsDetails(vararg nextItemDetails: NewsItemDetailsTable)

    @Delete
    suspend fun deleteAllItemsDetails(itemsDetails: List<NewsItemDetailsTable>)

    @Query("SELECT * FROM itemsWithDetails ORDER BY createdAt DESC, itemId")
    fun getAllItemsDetails(): Flow<List<NewsItemDetailsTable>>

    @Query("SELECT * FROM itemsWithDetails WHERE itemId = :neededId")
    fun getItemDetailsById(neededId: String): Flow<NewsItemDetailsTable>

}