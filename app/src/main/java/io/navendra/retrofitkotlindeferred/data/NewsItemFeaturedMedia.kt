package io.navendra.retrofitkotlindeferred.data

import com.squareup.moshi.Json

class NewsItemFeaturedMedia(
    @Json(name = "@id") val featuredMediaId: String? = null,
    @Json(name = "@type") val featuredMediaType: String? = null,
    @Json(name = "type") val featuredMediaTypeNum: String? = null,
    @Json(name = "provider") val featuredMediaProvider: String? = null,
    @Json(name = "uri") val featuredMediaUri: String? = null,
    @Json(name = "altText") val featuredMediaAltText: String? = null,
    @Json(name = "caption") val featuredMediaCaption: String? = null,
    @Json(name = "credit") val featuredMediaCredit: String? = null,
    @Json(name = "source") val featuredMediaSource: String? = null,
    @Json(name = "externalUrl") val featuredMediaExternalUrl: String? = null,
    @Json(name = "context") val featuredMediaContext: NewsItemFeaturedMediaContext? = null,
    @Json(name = "author") val featuredMediaAuthor: NewsItemFeaturedMediaAuthor? = null,
    @Json(name = "externalId") val featuredMediaExternalId: String? = null,
    @Json(name = "createdAt") val featuredMediaCreatedAt: String? = null,
    @Json(name = "updatedAt") val featuredMediaUpdatedAt: String? = null,
    @Json(name = "dailymotionTags") val featuredMediaDailymotionTags: List<String>? = null
)
