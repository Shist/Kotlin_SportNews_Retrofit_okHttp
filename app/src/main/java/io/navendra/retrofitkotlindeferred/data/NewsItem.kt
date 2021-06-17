package io.navendra.retrofitkotlindeferred.data

import com.google.gson.annotations.SerializedName

data class NewsItem(
    @SerializedName("@id") val id: String? = null,
    @SerializedName("@type") val type: String? = null,
    @SerializedName("author") val author: String? = null,
    @SerializedName("authorName") val authorName: String? = null,
    @SerializedName("body") val body: String? = null,
    @SerializedName("channels") val channels: List<NewsItemChannel>? = null,
    @SerializedName("createdAt") val createdAt: String? = null,
    @SerializedName("deletedAt") val deletedAt: String? = null,
    @SerializedName("expiredAt") val expiredAt: String? = null,
    @SerializedName("externalId") val externalId: String? = null,
    @SerializedName("externalUrl") val externalUrl: String? = null,
    @SerializedName("featured") val featured: Boolean? = null,
    @SerializedName("featuredMedia") val featuredMedia: NewsItemFeaturedMedia? = null,
    @SerializedName("headline") val headline: String? = null
    // TODO ещё дофига ключей
)
