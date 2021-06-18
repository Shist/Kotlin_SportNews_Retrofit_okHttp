package io.navendra.retrofitkotlindeferred.data

import com.google.gson.annotations.SerializedName

data class NewsItemFeaturedMediaAuthor(
    @SerializedName("@id") val featuredMediaAuthorId: String? = null,
    @SerializedName("@type") val featuredMediaAuthorType: String? = null,
    @SerializedName("name") val featuredMediaAuthorName: String? = null
)
