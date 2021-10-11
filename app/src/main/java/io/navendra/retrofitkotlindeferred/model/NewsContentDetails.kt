package io.navendra.retrofitkotlindeferred.model

import com.google.gson.annotations.SerializedName

data class NewsContentDetails(
    @SerializedName("hydra:member") val itemsDetails: List<NewsItemDetails>
)
