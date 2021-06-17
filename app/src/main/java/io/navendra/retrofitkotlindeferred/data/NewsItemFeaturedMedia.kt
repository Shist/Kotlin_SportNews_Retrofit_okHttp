package io.navendra.retrofitkotlindeferred.data

import com.google.gson.annotations.SerializedName

data class NewsItemFeaturedMedia(
    @SerializedName("@id") val featuredMediaId: String? = null,
    @SerializedName("@type") val featuredMediaType: String? = null,
    @SerializedName("type") val featuredMediaTypeNum: String? = null,
    @SerializedName("provider") val featuredMediaProvider: String? = null,
    @SerializedName("uri") val featuredMediaUri: String? = null,
    @SerializedName("altText") val featuredMediaAltText: String? = null,
    @SerializedName("caption") val featuredMediaCaption: String? = null,
    @SerializedName("credit") val featuredMediaCredit: String? = null,
    @SerializedName("source") val featuredMediaSource: String? = null,
    @SerializedName("externalUrl") val featuredMediaExternalUrl: String? = null,
    @SerializedName("context") val featuredMediaContext: NewsItemFeaturedMediaContext? = null,
    @SerializedName("author") val featuredMediaAuthor: NewsItemFeaturedMediaAuthor? = null,
    @SerializedName("externalId") val featuredMediaExternalId: String? = null,
    @SerializedName("createdAt") val featuredMediaCreatedAt: String? = null,
    @SerializedName("updatedAt") val featuredMediaUpdatedAt: String? = null,
    @SerializedName("dailymotionTags") val featuredMediaDailymotionTags: List<String>? = null
)
