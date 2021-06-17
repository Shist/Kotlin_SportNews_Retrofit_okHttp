package io.navendra.retrofitkotlindeferred.data

annotation class SerializedNameFeaturedMediaId(val featuredMediaId_: String)
annotation class SerializedNameFeaturedMediaType(val featuredMediaType_: String)
annotation class SerializedNameFeaturedMediaTypeNum(val featuredMediaTypeNum_: String)
annotation class SerializedNameFeaturedMediaProvider(val featuredMediaProvider_: String)
annotation class SerializedNameFeaturedMediaUri(val featuredMediaUri_: String)
annotation class SerializedNameFeaturedMediaAltText(val featuredMediaAltText_: String)
annotation class SerializedNameFeaturedMediaCaption(val featuredMediaCaption_: String)
annotation class SerializedNameFeaturedMediaCredit(val featuredMediaCredit_: String)
annotation class SerializedNameFeaturedMediaSource(val featuredMediaSource_: String)
annotation class SerializedNameFeaturedMediaExternalUrl(val featuredMediaExternalUrl_: String)
annotation class SerializedNameFeaturedMediaContext(val featuredMediaContext_: String)
annotation class SerializedNameFeaturedAuthor(val featuredMediaAuthor_: String)
annotation class SerializedNameFeaturedExternalId(val featuredMediaExternalId_: String)
annotation class SerializedNameFeaturedCreatedAt(val featuredMediaCreatedAt_: String)
annotation class SerializedNameFeaturedUpdatedAt(val featuredMediaUpdatedAt_: String)
annotation class SerializedNameFeaturedDailymotionTags(val featuredMediaDailymotionTags_: String)

data class NewsItemFeaturedMedia(
    @SerializedNameFeaturedMediaId("@id") val featuredMediaId_: String? = null,
    @SerializedNameFeaturedMediaType("@type") val featuredMediaType_: String? = null,
    @SerializedNameFeaturedMediaTypeNum("type") val featuredMediaTypeNum_: String? = null,
    @SerializedNameFeaturedMediaProvider("provider") val featuredMediaProvider_: String? = null,
    @SerializedNameFeaturedMediaUri("uri") val featuredMediaUri_: String? = null,
    @SerializedNameFeaturedMediaAltText("altText") val featuredMediaAltText_: String? = null,
    @SerializedNameFeaturedMediaCaption("caption") val featuredMediaCaption_: String? = null,
    @SerializedNameFeaturedMediaCredit("credit") val featuredMediaCredit_: String? = null,
    @SerializedNameFeaturedMediaSource("source") val featuredMediaSource_: String? = null,
    @SerializedNameFeaturedMediaExternalUrl("externalUrl") val featuredMediaExternalUrl_: String? = null,
    @SerializedNameFeaturedMediaContext("context") val featuredMediaContext_: NewsItemFeaturedMediaContext? = null,
    @SerializedNameFeaturedAuthor("author") val featuredMediaAuthor_: NewsItemFeaturedMediaAuthor? = null,
    @SerializedNameFeaturedExternalId("externalId") val featuredMediaExternalId_: String? = null,
    @SerializedNameFeaturedCreatedAt("createdAt") val featuredMediaCreatedAt_: String? = null,
    @SerializedNameFeaturedUpdatedAt("updatedAt") val featuredMediaUpdatedAt_: String? = null,
    @SerializedNameFeaturedDailymotionTags("dailymotionTags") val featuredMediaDailymotionTags_: List<String>? = null
)
