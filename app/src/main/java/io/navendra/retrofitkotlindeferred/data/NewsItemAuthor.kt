package io.navendra.retrofitkotlindeferred.data

import com.google.gson.annotations.SerializedName

data class NewsItemAuthor(
    @SerializedName("@id") val featuredMediaAuthorId: String? = null,
    @SerializedName("@type") val featuredMediaAuthorType: String? = null,
    val name: String? = null
)
