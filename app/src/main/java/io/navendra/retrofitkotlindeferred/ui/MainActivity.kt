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
// 1) В любом случае изначально сразу загружаем данные из БД
// 2) Крутить swipeRefreshLayout (маленький кружочек сверху) с самого начала (пока идёт попытка загрузить новые данные) N секунд
// 3) Если получилось загрузить, выводим новые новости, убираем swipeRefreshLayout
// 4) Если не получилось загрузить, выводим snackbar с ошибкой, убираем swipeRefreshLayout
// 5) Сделать сортировку новостей при перезагрузке (внутри map можно сделать SortBy... по дате), добавить поле даты (раскоментить в Json-овском айтеме)
// 5.2)* Посмотреть миграции базы данных
// 6)* Создать отдельную таблицу для одного item и загружать оттуда
