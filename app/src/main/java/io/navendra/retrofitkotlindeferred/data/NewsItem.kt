package io.navendra.retrofitkotlindeferred.data

annotation class SerializedNameId(val id_: String)
annotation class SerializedNameType(val type_: String)
annotation class SerializedNameAuthor(val author_: String)
annotation class SerializedNameAuthorName(val authorName_: String)
annotation class SerializedNameBody(val body_: String)
annotation class SerializedNameChannels(val channels_: String)
annotation class SerializedNameCreatedAt(val createdAt_: String)
annotation class SerializedNameDeletedAt(val deletedAt_: String)
annotation class SerializedNameExpiredAt(val expiredAt_: String)
annotation class SerializedNameExternalId(val externalId_: String)
annotation class SerializedNameExternalUrl(val externalUrl_: String)
annotation class SerializedNameFeatured(val featured_: String)
annotation class SerializedNameFeaturedMedia(val featuredMedia_: String)
annotation class SerializedNameHeadline(val headline_: String)
// TODO ещё дофига ключей

data class NewsItem(
    @SerializedNameId("@id") val id_: String? = null,
    @SerializedNameType("@type") val type_: String? = null,
    @SerializedNameAuthor("author") val author_: String? = null,
    @SerializedNameAuthorName("authorName") val authorName_: String? = null,
    @SerializedNameBody("body") val body_: String? = null,
    @SerializedNameChannels("channels") val channels_: List<NewsItemChannel>? = null,
    @SerializedNameCreatedAt("createdAt") val createdAt_: String? = null,
    @SerializedNameDeletedAt("deletedAt") val deletedAt_: String? = null,
    @SerializedNameExpiredAt("expiredAt") val expiredAt_: String? = null,
    @SerializedNameExternalId("externalId") val externalId_: String? = null,
    @SerializedNameExternalUrl("externalUrl") val externalUrl_: String? = null,
    @SerializedNameFeatured("featured") val featured_: String? = null,
    @SerializedNameFeaturedMedia("featuredMedia") val featuredMedia_: NewsItemFeaturedMedia? = null,
    @SerializedNameHeadline("headline") val headline_: String? = null
    // TODO ещё дофига ключей
)
