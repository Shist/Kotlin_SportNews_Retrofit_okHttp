package io.navendra.retrofitkotlindeferred.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import io.navendra.retrofitkotlindeferred.R
import io.navendra.retrofitkotlindeferred.service.SportNewsFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = SportNewsFactory.SPORT_NEWS_API

        // Getting News from News Content API
        GlobalScope.launch(Dispatchers.Main) {
            val userRequest = service.getNews()
            try {
                val response = userRequest.await()
                if(response.isSuccessful){
                    Log.d("MyLog", "Successful start logging...")
                    Log.d("MyLog", response.body().toString())
                }else{
                    Log.d("MyLog", "Failure start logging...")
                    Log.d("MyLog", response.errorBody().toString())
                }
            }catch (e: Exception){
                Log.d("MyLog", "Failure...")
                Log.d("MyLog", e.toString())
            }
        }

    }
}
