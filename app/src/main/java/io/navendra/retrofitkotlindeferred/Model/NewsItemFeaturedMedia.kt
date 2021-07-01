package io.navendra.retrofitkotlindeferred.Model

import com.google.gson.annotations.SerializedName

// Закомментированы те данные, которые нам пока что не нужны для ленты новостей...

data class NewsItemFeaturedMedia(
    //@SerializedName("@id") val featuredMediaId: String,
    //@SerializedName("@type") val featuredMediaType: String,
    //@SerializedName("type") val featuredMediaTypeNum: String,
    //@SerializedName("provider") val featuredMediaProvider: String,
    @SerializedName("uri") val featuredMediaUri: String,
    @SerializedName("altText") val featuredMediaAltText: String,
    //@SerializedName("caption") val featuredMediaCaption: String,
    //@SerializedName("credit") val featuredMediaCredit: String,
    //@SerializedName("source") val featuredMediaSource: String,
    //@SerializedName("externalUrl") val featuredMediaExternalUrl: String,
    //@SerializedName("context") val featuredMediaContext: NewsItemFeaturedMediaContext,
    //@SerializedName("author") val featuredMediaAuthor: NewsItemAuthor,
    //@SerializedName("externalId") val featuredMediaExternalId: String,
    //@SerializedName("createdAt") val featuredMediaCreatedAt: String,
    //@SerializedName("updatedAt") val featuredMediaUpdatedAt: String,
    //@SerializedName("dailymotionTag") val featuredMediaDailymotionTags: List<String>
)
