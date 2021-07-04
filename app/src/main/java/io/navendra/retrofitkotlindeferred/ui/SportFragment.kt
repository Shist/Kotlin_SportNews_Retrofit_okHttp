package io.navendra.retrofitkotlindeferred.ui

import SportAdapter
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.navendra.retrofitkotlindeferred.Model.NewsItem
import io.navendra.retrofitkotlindeferred.R

class SportFragment : Fragment() {

    companion object {
        fun newInstance() = SportFragment()
    }

    private lateinit var viewModel: SportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sport, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadData()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.news.observe(this.viewLifecycleOwner, Observer { news ->

            recyclerView.adapter = SportAdapter(requireContext(), news)
        })
        viewModel.loadData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(this).get(SportViewModel::class.java)
    }

}

// Два фрагмента: для блока новостей и для деталей одной какой-то новости (при нажатии на неё)