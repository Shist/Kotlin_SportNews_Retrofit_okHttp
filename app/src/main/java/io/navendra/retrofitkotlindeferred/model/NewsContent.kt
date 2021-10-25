package io.navendra.retrofitkotlindeferred.model

import com.google.gson.annotations.SerializedName

data class NewsContent(
    @SerializedName("hydra:member") val items: List<NewsItem>?
)
