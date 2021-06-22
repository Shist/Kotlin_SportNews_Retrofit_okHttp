package io.navendra.retrofitkotlindeferred.service
import io.navendra.retrofitkotlindeferred.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory{

    private val loggingInterceptor =  HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    //Not logging the authkey if not debug
    private val client =
        if(BuildConfig.DEBUG){
             OkHttpClient().newBuilder()
                    .addInterceptor(loggingInterceptor)
                    .build()
        }else{
            OkHttpClient().newBuilder()
                    .addInterceptor(loggingInterceptor)
                    .build()
        }

    fun retrofit(baseUrl : String) : Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}