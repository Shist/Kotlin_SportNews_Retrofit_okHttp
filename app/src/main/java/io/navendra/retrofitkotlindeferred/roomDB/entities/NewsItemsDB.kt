package io.navendra.retrofitkotlindeferred.roomDB.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import io.navendra.retrofitkotlindeferred.model.NewsItemFeaturedMedia

@Entity(tableName = "items")
data class NewsItemsDB(

    @PrimaryKey
    @SerializedName("@id")
    var itemId: String,

    @ColumnInfo(name = "body")
    @SerializedName("body")
    val body: String,

    @ColumnInfo(name = "featuredMedia")
    @SerializedName("featuredMedia")
    val featuredMedia: NewsItemFeaturedMedia,

    @ColumnInfo(name = "shortHeadLine")
    @SerializedName("shortHeadline")
    var shortHeadline: String

)
