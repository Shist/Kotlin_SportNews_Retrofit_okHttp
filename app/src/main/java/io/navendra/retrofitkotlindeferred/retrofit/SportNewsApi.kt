package io.navendra.retrofitkotlindeferred.retrofit

import io.navendra.retrofitkotlindeferred.model.NewsContent
import io.navendra.retrofitkotlindeferred.model.NewsContentDetails
import retrofit2.http.GET

interface SportNewsApi{

    @GET("contents?taxonomy[]=633&order[publishedAt]=desc&status=5&channel=1&site=4&itemsPerPage=50")
    suspend fun getNews() : NewsContent

    @GET("contents?taxonomy[]=633&order[publishedAt]=desc&status=5&channel=1&site=4&itemsPerPage=50")
    suspend fun getNewsDetails() : NewsContentDetails

}