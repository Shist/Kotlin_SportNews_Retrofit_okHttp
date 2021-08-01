package io.navendra.retrofitkotlindeferred.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.navendra.retrofitkotlindeferred.databinding.NewsItemsListBinding

class NewsListFragment : Fragment() {

    private lateinit var viewModel: SportViewModel

    lateinit var swipeContainer: SwipeRefreshLayout

    private var _binding: NewsItemsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewsItemsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadData()

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = SportAdapter {
            val myActivity = requireActivity() as MainActivity
            myActivity.onItemClick(it.id)
        }
        recyclerView.adapter = adapter

        viewModel.news.observe(this.viewLifecycleOwner, {
            adapter.setItems(it)
            adapter.notifyDataSetChanged()
            swipeContainer.isRefreshing = false
        })

        // (*) Посмотреть ListAdapter

        swipeContainer = binding.swipeContainer
        swipeContainer.setOnRefreshListener {
            viewModel.loadData()
        }
        swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(requireActivity()).get(SportViewModel::class.java)
    }

}
