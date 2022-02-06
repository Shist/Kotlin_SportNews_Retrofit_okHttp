package ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.ListFragment
import ui.databinding.ActivityMainBinding
import ui.fragments.NewsListFragment
import ui.fragments.NewsPageFragment
import ui.fragments.NoItemSelectedFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var orientation: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orientation = resources.configuration.orientation

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val currItemId = intent.getStringExtra("currItemId")

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (currItemId == null || currItemId == "no_item_selected") {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, NewsListFragment())
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,
                        NewsPageFragment.newInstance(currItemId))
                    .addToBackStack("goBack")
                    .commit()
            }
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NewsListFragment())
                .commit()
            if (currItemId == null || currItemId == "no_item_selected") {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_2, NoItemSelectedFragment())
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_2,
                        NewsPageFragment.newInstance(currItemId))
                    .commit()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        intent.putExtra("currItemId", "no_item_selected")
    }

    fun onItemClick(itemID: String) {
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, NewsPageFragment.newInstance(itemID))
                .addToBackStack("goBack")
                .commit()
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_2, NewsPageFragment.newInstance(itemID))
                .addToBackStack("goBack")
                .commit()
        }
    }

}
