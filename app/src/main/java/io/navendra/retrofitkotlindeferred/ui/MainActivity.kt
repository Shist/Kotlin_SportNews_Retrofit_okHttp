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
// 1) loadData (ListViewModel.kt) лучше убрать из init (и вызывать где-нибудь ранее... например, во фрагменте)
// 2) В любом случае изначально сразу загружаем данные из БД
// 3) Крутить swipeRefreshLayout (маленький кружочек сверху) с самого начала (пока идёт попытка загрузить новые данные) N секунд
// 4) Если получилось загрузить, выводим новые новости, убираем swipeRefreshLayout
// 5) Если не получилось загрузить, выводим snackbar с ошибкой, убираем swipeRefreshLayout
// 6)* Создать отдельную таблицу для одного item и загружать оттуда
