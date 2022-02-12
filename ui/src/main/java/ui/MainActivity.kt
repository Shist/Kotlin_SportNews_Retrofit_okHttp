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

    // 1) Сделать это через ViewModel (пробросить ItemID) и во вьюхах и в compose (вместо intent)
    // 2) Убрать NavController для landscape-а в compose версии (оставив его для portrait-ной)

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

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (currItemId == "no_item_selected") {
                inflateFragment(NewsListFragment(),
                    R.id.fragment_container,false)
            } else {
                inflateFragment(NewsPageFragment.newInstance(currItemId),
                    R.id.fragment_container,true)
            }
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            inflateFragment(NewsListFragment(),
                R.id.fragment_container,false)
            if (currItemId == "no_item_selected") {
                inflateFragment(NoItemSelectedFragment(),
                    R.id.fragment_container_2,false)
            } else {
                inflateFragment(NewsPageFragment.newInstance(currItemId),
                    R.id.fragment_container_2,false)
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
                R.id.fragment_container,true)
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            inflateFragment(NewsPageFragment.newInstance(itemID),
                R.id.fragment_container_2,false)
        }
    }

}
