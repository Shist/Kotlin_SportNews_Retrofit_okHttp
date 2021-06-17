package io.navendra.retrofitkotlindeferred.data

import com.squareup.moshi.Json

class NewsContent(
    @Json(name = "hydra:member") val items: List<NewsItem>? = null
)
