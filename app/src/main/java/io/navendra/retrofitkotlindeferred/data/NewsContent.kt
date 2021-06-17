package io.navendra.retrofitkotlindeferred.data

annotation class SerializedNameDataItems(val items_: String)

data class NewsContent(
    @SerializedNameDataItems("hydra:member") val items_: List<NewsItem>? = null
)
