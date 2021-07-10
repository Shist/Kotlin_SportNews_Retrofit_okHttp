package io.navendra.retrofitkotlindeferred.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsClient
import kotlinx.coroutines.*

class SportViewModel : ViewModel() {

    val news = MutableLiveData<List<NewsItem>>()

    fun loadData(){

        val scope = CoroutineScope(Job() + Dispatchers.Main)

        val service = SportNewsClient.SPORT_NEWS_API

        viewModelScope.launch(Dispatchers.Main) {
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