package io.navendra.retrofitkotlindeferred.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.navendra.retrofitkotlindeferred.R
import io.navendra.retrofitkotlindeferred.service.SportNewsFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // Создать класс ViewModel - промежуточный между MainActivity и UI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = SportNewsFactory.SPORT_NEWS_API

        // Getting News from News Content API
        // RecycleView вместо ScrollView
        // Два фрагмента: для блока новостей и для деталей одной какой-то новости (при нажатии на неё)


        GlobalScope.launch(Dispatchers.Main) {
            try {
                val userRequest = service.getNews()

                val response = userRequest
                Log.d("MyLog", "response: ${response.items.size}")
//                if(response.isSuccessful){
//                    Log.d("MyLog", "Successful start logging...")
//                    Log.d("MyLog", response.body().toString())
//                }else{
//                    Log.d("MyLog", "Failure start logging...")
//                    Log.d("MyLog", response.errorBody().toString())
//                }
            } catch (e: Throwable){
                Log.d("MyLog", "Failure...", e)
            }
        }

    }


}
