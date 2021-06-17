package io.navendra.retrofitkotlindeferred.data

import com.squareup.moshi.Json

class NewsItemChannel(
    @Json(name = "@id") val channelId: String? = null,
    @Json(name = "@type") val channelType: String? = null,
    @Json(name = "name") val channelName: String? = null
)
