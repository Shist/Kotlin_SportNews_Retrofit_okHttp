package io.navendra.retrofitkotlindeferred.model.newsItem

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import io.navendra.retrofitkotlindeferred.model.NewsItemFeaturedMedia

@Entity
data class NewsItem(

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

    // Закомментированы те данные, которые нам пока что не нужны для ленты новостей...

    //@SerializedName("@type") val type: String,
    //@SerializedName("author") val author: NewsItemAuthor,
    //@SerializedName("authorName") val authorName: String,
    //@SerializedName("channels") val channels: List<NewsItemChannel>,
    //@SerializedName("createdAt") val createdAt: String,
    //@SerializedName("deletedAt") val deletedAt: String,
    //@SerializedName("expiredAt") val expiredAt: String,
    //@SerializedName("externalId") val externalId: String,
    //@SerializedName("externalUrl") val externalUrl: String,
    //@SerializedName("featured") val featured: Boolean,
    //@SerializedName("headline")  val headline: String,

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable(NewsItemFeaturedMedia::class.java.classLoader)!!,
        parcel.readString()!!
    ) {
    }

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(itemId)
        parcel.writeString(body)
        parcel.writeParcelable(featuredMedia, 0)
        parcel.writeString(shortHeadline)
    }

    companion object CREATOR : Parcelable.Creator<NewsItem> {
        override fun createFromParcel(parcel: Parcel): NewsItem {
            return NewsItem(parcel)
        }

        override fun newArray(size: Int): Array<NewsItem?> {
            return arrayOfNulls(size)
        }
    }
}
