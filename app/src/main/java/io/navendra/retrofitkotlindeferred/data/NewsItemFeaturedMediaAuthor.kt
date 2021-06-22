package io.navendra.retrofitkotlindeferred.data

import com.google.gson.annotations.SerializedName

// Этот класс нам пока что не нужен, но может пригодится в будущем

data class NewsItemFeaturedMediaAuthor(
    @SerializedName("@id") val featuredMediaAuthorId: String,
    @SerializedName("@type") val featuredMediaAuthorType: String,
    @SerializedName("name") val featuredMediaAuthorName: String
)
