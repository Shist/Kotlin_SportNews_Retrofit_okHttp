package data.retrofit

import data.model.NewsContentDetailsJson
import data.model.NewsContentJson
import retrofit2.http.GET

interface SportNewsApi{

    @GET("contents?taxonomy[]=633&order[publishedAt]=desc&status=5&channel=1&site=4&itemsPerPage=50")
    suspend fun getNews() : NewsContentJson

    @GET("contents?taxonomy[]=633&order[publishedAt]=desc&status=5&channel=1&site=4&itemsPerPage=50")
    suspend fun getNewsDetails() : NewsContentDetailsJson

}