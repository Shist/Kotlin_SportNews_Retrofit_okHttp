package ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ui.databinding.ActivityMainBinding
import ui.fragments.NewsListFragment
import ui.fragments.NewsPageFragment

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
