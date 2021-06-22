package io.navendra.retrofitkotlindeferred.data

import com.google.gson.annotations.SerializedName

data class NewsContent(
    @SerializedName("hydra:member") val items: List<NewsItem>
)
