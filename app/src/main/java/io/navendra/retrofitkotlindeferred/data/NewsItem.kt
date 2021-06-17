package io.navendra.retrofitkotlindeferred.data

import com.squareup.moshi.Json

class NewsItem(
    @Json(name = "@id") val id: String? = null,
    @Json(name = "@type") val type: String? = null,
    @Json(name = "author") val author: String? = null,
    @Json(name = "authorName") val authorName: String? = null,
    @Json(name = "body") val body: String? = null,
    @Json(name = "channels") val channels: List<NewsItemChannel>? = null,
    @Json(name = "createdAt") val createdAt: String? = null,
    @Json(name = "deletedAt") val deletedAt: String? = null,
    @Json(name = "expiredAt") val expiredAt: String? = null,
    @Json(name = "externalId") val externalId: String? = null,
    @Json(name = "externalUrl") val externalUrl: String? = null,
    @Json(name = "featured") val featured: String? = null,
    @Json(name = "featuredMedia") val featuredMedia: NewsItemFeaturedMedia? = null,
    @Json(name = "headline") val headline: String? = null
    // TODO ещё дофига ключей
)
