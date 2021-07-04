package io.navendra.retrofitkotlindeferred.ui

import SportAdapter
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.navendra.retrofitkotlindeferred.R

class MainActivity : AppCompatActivity() {

    private lateinit var sportFragment: SportFragment
    private lateinit var sportAdapter: SportAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sportFragment = SportFragment()
    }

    //добавить сюда фрагмент, в нём создать адаптер и заполнить его RecycleView
}
