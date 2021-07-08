package io.navendra.retrofitkotlindeferred.model

import com.google.gson.annotations.SerializedName

data class NewsItemFeaturedMediaContext(
    @SerializedName("thumbnail_resized_800") val featuredMediaContext: String
)
