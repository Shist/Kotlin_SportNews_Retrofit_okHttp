package com.example.data.model

import com.google.gson.annotations.SerializedName

// Этот класс нам пока что не нужен, но может пригодится в будущем

data class NewsItemChannelJson(
    @SerializedName("@id") val channelId: String?,
    @SerializedName("@type") val channelType: String?,
    @SerializedName("name") val channelName: String?
)
