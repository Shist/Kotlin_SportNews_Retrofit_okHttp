package io.navendra.retrofitkotlindeferred.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.navendra.retrofitkotlindeferred.R
import io.navendra.retrofitkotlindeferred.model.NewsItem

class MainActivity : AppCompatActivity() {
    lateinit var swipeContainer: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NewsListFragment())
            .commit()

        swipeContainer = findViewById(R.id.swipeContainer)
        swipeContainer.setOnRefreshListener {

        }
        swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);
    }

    fun onItemClick(item: NewsItem) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsPageFragment(item))
            .addToBackStack("Вернуться")
            .commit()
    }

}

/*
TODO
1) Мы используем просто Adapter, а можно ещё посмотреть "ListAdapter", его фишка в том, что он уже содержит в себе список, в нем есть submitItems, в нём есть "DifUtils" (смотрит старый и новый списки, находит разницу, сравнивает, если ничего не поменялось - он ничего не делает). Есть ListAdapter для ListView (старый, не смотрим его), а нам нужен второй - более новый - ListAdapter для recycle view.
2) Посмотреть ViewBinding для адаптера
 */