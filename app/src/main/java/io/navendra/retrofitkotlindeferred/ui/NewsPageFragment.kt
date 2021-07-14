package io.navendra.retrofitkotlindeferred.ui

import SportAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.navendra.retrofitkotlindeferred.R
import io.navendra.retrofitkotlindeferred.model.NewsItem

class NewsPageFragment (news : NewsItem) : Fragment() {

    private lateinit var viewModel: SportViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycle_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadData()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.news.observe(this.viewLifecycleOwner, { news ->
            //TODO Вызывать адаптер НЕ каждый раз при загрузке данных
            val adapter = SportAdapter(news) {
                val myActivity = requireActivity() as MainActivity
                myActivity.onItemClick(it)
            }
            recyclerView.adapter = adapter
        })
        // (*) Посмотреть ListAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(this).get(SportViewModel::class.java)
    }
}