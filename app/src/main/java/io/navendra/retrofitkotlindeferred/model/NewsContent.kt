package io.navendra.retrofitkotlindeferred.model

import com.google.gson.annotations.SerializedName
import io.navendra.retrofitkotlindeferred.model.newsItem.NewsItem

data class NewsContent(
    @SerializedName("hydra:member") val items: List<NewsItem>
)
