package io.navendra.retrofitkotlindeferred.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.navendra.retrofitkotlindeferred.R
import io.navendra.retrofitkotlindeferred.databinding.ActivityMainBinding
import io.navendra.retrofitkotlindeferred.ui.fragments.NewsListFragment
import io.navendra.retrofitkotlindeferred.ui.fragments.NewsPageFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null)
        {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NewsListFragment())
                .commit()
        }
    }

    fun onItemClick(itemID: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsPageFragment.newInstance(itemID))
            .addToBackStack("goBack")
            .commit()
    }

}

// TODO
// 1) Если не получилось загрузить, выводим snackbar с ошибкой, убираем swipeRefreshLayout
// 2)* Посмотреть миграции базы данных
// 3)* Создать отдельную таблицу для одного item и загружать оттуда
// 4) WorkManager - как сделать с ним DailyUpdates
