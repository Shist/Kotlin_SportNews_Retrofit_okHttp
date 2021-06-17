package io.navendra.retrofitkotlindeferred.data

import com.squareup.moshi.Json

class NewsItemFeaturedMediaAuthor(
    @Json(name = "@id") val featuredMediaAuthorId: String? = null,
    @Json(name = "@type") val featuredMediaAuthorType: String? = null,
    @Json(name = "name") val featuredMediaAuthorName: String? = null
)
