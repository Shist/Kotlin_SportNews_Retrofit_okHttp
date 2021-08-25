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

// TODO 1) в Маппере должен остаться один метод (убрать все, а в местах их использования вызываать map { ... })
// TODO 2) Убрать GetInstance в методах репозитория (доисправить, если он ещё не всё там дофиксил)
// TODO 3) Везде, где это возможно, сразу заменить item-ы с Json на БД-шные (тогда можно будет и Parcelable для item убрать)
// TODO 4) Сделать LoadNewsOfItem(itemId: String) в репозитории
// TODO 5) Сделать везде itemID вместо item_id