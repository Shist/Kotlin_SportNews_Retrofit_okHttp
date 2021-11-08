package io.navendra.retrofitkotlindeferred.model

import com.google.gson.annotations.SerializedName

data class NewsContentDetailsJson(
    @SerializedName("hydra:member") val itemsDetails: List<NewsItemDetailsJson>?
)
