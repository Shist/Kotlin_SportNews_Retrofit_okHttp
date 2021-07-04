package io.navendra.retrofitkotlindeferred.ui

import SportAdapter
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.navendra.retrofitkotlindeferred.Model.NewsItem
import io.navendra.retrofitkotlindeferred.R

class SportFragment : Fragment() {

    private var sportAdapter : SportAdapter? = null

    var news = MutableLiveData<List<NewsItem>>()

    var imgUri : String? = null
    var headline : String? = null
    var altText : String? = null

    companion object {
        fun newInstance() = SportFragment()
    }

    private lateinit var viewModel: SportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sport_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.news.observe(this.viewLifecycleOwner, Observer {})
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SportViewModel::class.java)
        viewModel.loadData()

        news = viewModel.news

        imgUri = viewModel.news.value?.get(0).toString()
        headline = viewModel.news.value?.get(1).toString()
        altText = viewModel.news.value?.get(2).toString()

        sportAdapter = SportAdapter()
    }

}

// Два фрагмента: для блока новостей и для деталей одной какой-то новости (при нажатии на неё)