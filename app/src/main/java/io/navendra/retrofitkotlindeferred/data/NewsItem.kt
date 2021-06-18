package io.navendra.retrofitkotlindeferred.data

import com.google.gson.annotations.SerializedName

data class NewsItem(
    //@SerializedName("@id") val id: String? = null,
    //@SerializedName("@type") val type: String? = null,
    //val author: NewsItemAuthor? = null,
    //val authorName: String? = null,
    //val body: String? = null,
    //val channels: List<NewsItemChannel>? = null,
    //val createdAt: String? = null,
    //val deletedAt: String? = null,
    //val expiredAt: String? = null,
    //val externalId: String? = null,
    //val externalUrl: String? = null,
    //val featured: Boolean? = null,
    val featuredMedia: NewsItemFeaturedMedia? = null,
    //val headline: String? = null,
    var shortHeadline: String? = null
)
