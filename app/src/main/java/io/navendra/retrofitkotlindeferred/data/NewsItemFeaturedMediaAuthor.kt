package io.navendra.retrofitkotlindeferred.data

annotation class SerializedNameFeaturedMediaAuthorId(val featuredMediaAuthorId_: String)
annotation class SerializedNameFeaturedMediaAuthorType(val featuredMediaAuthorType_: String)
annotation class SerializedNameFeaturedMediaAuthorName(val featuredMediaAuthorName_: String)

data class NewsItemFeaturedMediaAuthor(
    @SerializedNameFeaturedMediaAuthorId("@id") val featuredMediaAuthorId_: String? = null,
    @SerializedNameFeaturedMediaAuthorType("@type") val featuredMediaAuthorType_: String? = null,
    @SerializedNameFeaturedMediaAuthorName("name") val featuredMediaAuthorName_: String? = null
)
