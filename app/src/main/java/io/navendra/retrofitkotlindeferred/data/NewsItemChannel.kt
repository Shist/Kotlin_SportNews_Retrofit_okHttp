package io.navendra.retrofitkotlindeferred.data

import com.google.gson.annotations.SerializedName

data class NewsItemChannel(
    @SerializedName("@id") val channelId: String? = null,
    @SerializedName("@type") val channelType: String? = null,
    val name: String? = null
)
