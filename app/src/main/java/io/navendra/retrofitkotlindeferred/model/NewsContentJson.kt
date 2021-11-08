package io.navendra.retrofitkotlindeferred.model

import com.google.gson.annotations.SerializedName

data class NewsContentJson(
    @SerializedName("hydra:member") val itemJsons: List<NewsItemJson>?
)
