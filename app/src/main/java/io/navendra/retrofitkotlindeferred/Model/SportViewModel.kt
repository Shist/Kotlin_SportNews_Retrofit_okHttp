package io.navendra.retrofitkotlindeferred.Model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.navendra.retrofitkotlindeferred.Retrofit.SportNewsClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SportViewModel : ViewModel() {

    val news = MutableLiveData<List<NewsItem>>()


    fun loadData(){
        val service = SportNewsClient.SPORT_NEWS_API

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val userRequest = service.getNews()
                news.value = userRequest.items
                if(userRequest.items.isNotEmpty()){
                    Log.d("MyLog", "Successful start logging...")
                    Log.d("MyLog", "response: ${userRequest.items.size} items")
                    for (i in userRequest.items.indices)
                        Log.d("MyLog", userRequest.items[i].toString())
                }else{
                    Log.d("MyLog", "Failure while getting response...")
                }
            } catch (e: Throwable){
                Log.d("MyLog", "Failure...", e)
            }
        }
    }

        override fun onCleared() {
            super.onCleared()
            Log.i("SportViewModel", "SportViewModel destroyed!")
        }
    }