package com.example.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class NewsItemFeaturedMediaContextJson(
    @SerializedName("thumbnail_resized_800") val featuredMediaContext: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()!!)

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(featuredMediaContext)
    }

    companion object CREATOR : Parcelable.Creator<NewsItemFeaturedMediaContextJson> {
        override fun createFromParcel(parcel: Parcel): NewsItemFeaturedMediaContextJson {
            return NewsItemFeaturedMediaContextJson(parcel)
        }

        override fun newArray(size: Int): Array<NewsItemFeaturedMediaContextJson?> {
            return arrayOfNulls(size)
        }
    }
}
