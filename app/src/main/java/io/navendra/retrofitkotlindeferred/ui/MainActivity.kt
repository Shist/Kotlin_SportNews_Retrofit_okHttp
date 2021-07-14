package io.navendra.retrofitkotlindeferred.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.navendra.retrofitkotlindeferred.R
import io.navendra.retrofitkotlindeferred.model.NewsItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SportFragment())
            .commit()
    }

    fun onItemClick(news: NewsItem) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, SportPageFragment(news))
            .addToBackStack("Вернуться")
            .commit()
    }

//    fun onItemClick() {
//        (context as MainActivity).supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragment_container, SportFragment())
//            .commit()
//    }

}
