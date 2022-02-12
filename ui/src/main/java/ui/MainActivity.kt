package ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ui.databinding.ActivityMainBinding
import ui.fragments.NewsListFragment
import ui.fragments.NewsPageFragment
import ui.fragments.NoItemSelectedFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currItemId = "no_item_selected"
    private var orientation: Int = 0

    private fun inflateFragment(f: Fragment, holder: Int, needAddBackStackOrNot: Boolean) {
        if (needAddBackStackOrNot) {
            supportFragmentManager.beginTransaction()
                .replace(holder, f)
                .addToBackStack("goBack")
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(holder, f)
                .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("currItemId", currItemId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orientation = resources.configuration.orientation

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState != null) {
            currItemId = savedInstanceState.getString("currItemId", "no_item_selected")
        }

        // Если ориентация портретная, то...
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // В любом случае раздуваем фрагмент со списком новостей
            inflateFragment(NewsListFragment(),
                R.id.fragment_container_portrait,false)
            // Если же есть открытая новость, то раздуваем ещё и её сверху (добавляя в стек)
            if (currItemId != "no_item_selected") {
                inflateFragment(NewsPageFragment.newInstance(currItemId),
                    R.id.fragment_container_portrait,true)
            }
            // Если же ориентация альбомая, то...
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Чистим стек в ноль, если в него был добавлен какой-то открытый айтем
            if (supportFragmentManager.backStackEntryCount != 0) {
                supportFragmentManager.popBackStack()
            }
            // В любом случае раздуваем фрагмент со списком новостей слева
            inflateFragment(NewsListFragment(),
                R.id.fragment_container_landscape_1,false)
            if (currItemId == "no_item_selected") {
                // Если ничего открыто не было, то раздуваем справа пустышку
                inflateFragment(NoItemSelectedFragment(),
                    R.id.fragment_container_landscape_2,false)
            } else {
                // Если же что-то было открыто, то раздуваем это справа
                inflateFragment(NewsPageFragment.newInstance(currItemId),
                    R.id.fragment_container_landscape_2,false)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        currItemId = "no_item_selected"
    }

    fun onItemClick(itemID: String) {
        currItemId = itemID
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            inflateFragment(NewsPageFragment.newInstance(itemID),
                R.id.fragment_container_portrait,true)
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            inflateFragment(NewsPageFragment.newInstance(itemID),
                R.id.fragment_container_landscape_2,false)
        }
    }

}
