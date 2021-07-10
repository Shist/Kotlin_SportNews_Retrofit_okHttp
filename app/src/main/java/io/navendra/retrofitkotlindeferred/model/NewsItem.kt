package io.navendra.retrofitkotlindeferred.model

import com.google.gson.annotations.SerializedName

// Закомментированы те данные, которые нам пока что не нужны для ленты новостей...

data class NewsItem(
    //@SerializedName("@id") val id: String,
    //@SerializedName("@type") val type: String,
    //@SerializedName("author") val author: NewsItemAuthor,
    //@SerializedName("authorName") val authorName: String,
    @SerializedName("body") val body: String,
    //@SerializedName("channels") val channels: List<NewsItemChannel>,
    //@SerializedName("createdAt") val createdAt: String,
    //@SerializedName("deletedAt") val deletedAt: String,
    //@SerializedName("expiredAt") val expiredAt: String,
    //@SerializedName("externalId") val externalId: String,
    //@SerializedName("externalUrl") val externalUrl: String,
    //@SerializedName("featured") val featured: Boolean,
    @SerializedName("featuredMedia") val featuredMedia: NewsItemFeaturedMedia,
    //@SerializedName("headline")  val headline: String,
    @SerializedName("shortHeadline") var shortHeadline: String
)
