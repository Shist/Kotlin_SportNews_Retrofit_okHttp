package io.navendra.retrofitkotlindeferred.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

// Закомментированы те данные, которые нам пока что не нужны для ленты новостей...

data class NewsItem(
    @SerializedName("@id") var id: String,
    //@SerializedName("@type") val type: String?,
    //@SerializedName("author") val author: NewsItemAuthor?,
    //@SerializedName("authorName") val authorName: String?,
    //@SerializedName("body") val body: String?,
    //@SerializedName("channels") val channels: List<NewsItemChannel?>?,
    @SerializedName("createdAt") val createdAt: String?,
    //@SerializedName("deletedAt") val deletedAt: String?,
    //@SerializedName("expiredAt") val expiredAt: String?,
    //@SerializedName("externalId") val externalId: String?,
    //@SerializedName("externalUrl") val externalUrl: String?,
    //@SerializedName("featured") val featured: Boolean?,
    @SerializedName("featuredMedia") val featuredMedia: NewsItemFeaturedMedia?,
    //@SerializedName("headline")  val headline: String?,
    @SerializedName("shortHeadline") var shortHeadline: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable(NewsItemFeaturedMedia::class.java.classLoader)!!,
        parcel.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(createdAt)
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