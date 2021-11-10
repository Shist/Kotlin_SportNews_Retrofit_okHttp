package data.model

import com.google.gson.annotations.SerializedName

data class NewsContentJson(
    @SerializedName("hydra:member") val itemJsons: List<NewsItemJson>?
)
