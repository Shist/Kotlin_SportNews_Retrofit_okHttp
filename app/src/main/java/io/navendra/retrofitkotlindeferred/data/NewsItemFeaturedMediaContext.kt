package io.navendra.retrofitkotlindeferred.data

import com.google.gson.annotations.SerializedName

// Этот класс нам пока что не нужен, но может пригодится в будущем

data class NewsItemFeaturedMediaContext(
    @SerializedName("thumbnail_resized_800") val featuredMediaContext: String
)
