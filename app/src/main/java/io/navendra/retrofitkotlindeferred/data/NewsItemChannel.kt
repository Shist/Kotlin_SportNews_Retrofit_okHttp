package io.navendra.retrofitkotlindeferred.data

annotation class SerializedNameChannelId(val channelId_: String)
annotation class SerializedNameChannelType(val channelType_: String)
annotation class SerializedNameChannelName(val channelName_: String)

data class NewsItemChannel(
    @SerializedNameChannelId("@id") val channelId_: String? = null,
    @SerializedNameChannelType("@type") val channelType_: String? = null,
    @SerializedNameChannelName("name") val channelName_: String? = null
)
