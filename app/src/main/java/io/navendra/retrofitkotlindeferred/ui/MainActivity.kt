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

    fun openNewsDetails(page: SportPageFragment) {
        // Он просит дать SportFragment, но мы даём NewsItem (нужно дать новый фрагмент - NewsDetailsFragment)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, page).commit()
    }

}
