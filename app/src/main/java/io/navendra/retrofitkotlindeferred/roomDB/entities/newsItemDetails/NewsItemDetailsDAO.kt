package io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsItemDetailsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneItemDetails(itemDetails: NewsItemDetailsTableImpl)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSomeItemsDetails(vararg nextItemDetails: NewsItemDetailsTableImpl)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemsDetailsList(itemsDetails: List<NewsItemDetailsTableImpl>)

    @Update
    suspend fun updateOneItemDetails(itemDetails: NewsItemDetailsTableImpl)

    @Update
    suspend fun updateSomeItemsDetails(vararg nextItem: NewsItemDetailsTableImpl)

    @Update
    suspend fun updateAllItemsDetails(itemsDetails: List<NewsItemDetailsTableImpl>)

    @Delete
    suspend fun deleteOneItemDetails(itemDetails: NewsItemDetailsTableImpl)

    @Delete
    suspend fun deleteSomeItemsDetails(vararg nextItemDetails: NewsItemDetailsTableImpl)

    @Delete
    suspend fun deleteAllItemsDetails(itemsDetails: List<NewsItemDetailsTableImpl>)

    @Query("SELECT * FROM itemsWithDetails ORDER BY createdAt DESC, itemId")
    fun getAllItemsDetails(): Flow<List<NewsItemDetailsTableImpl>>

    @Query("SELECT * FROM itemsWithDetails WHERE itemId = :neededId")
    fun getItemDetailsById(neededId: String): Flow<NewsItemDetailsTableImpl>

}