gitpackage io.navendra.retrofitkotlindeferred.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import io.navendra.retrofitkotlindeferred.R
import io.navendra.retrofitkotlindeferred.data.NewsContent
import io.navendra.retrofitkotlindeferred.service.SportNewsFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = SportNewsFactory.SPORT_NEWS_API

        var response: NewsContent? = null

        // Getting News from News Content API
        GlobalScope.launch(Dispatchers.Main) {
            try {
            val userRequest = service.getNews()

                response = userRequest
                Log.d("MyLog", "response: ${response?.items?.size}")

            } catch (e: Throwable){
                Log.d("MyLog", "Failure...", e)
            }
        }

        // UI operations


    }


}
