package io.navendra.retrofitkotlindeferred.ui.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import io.navendra.retrofitkotlindeferred.databinding.NewsItemsListBinding
import io.navendra.retrofitkotlindeferred.ui.MainActivity
import io.navendra.retrofitkotlindeferred.ui.adapter.SportAdapter
import io.navendra.retrofitkotlindeferred.ui.viewModel.NewsListViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsListFragment : Fragment() {

    private lateinit var viewModel: NewsListViewModel

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

        val swipeContainer = binding.swipeContainer
        swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        if (savedInstanceState == null && !viewModel.isLoaded) {
            swipeContainer.post { swipeContainer.isRefreshing = true }
            viewModel.loadData()
            viewModel.isLoaded = true
        }

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = SportAdapter {
            val myActivity = requireActivity() as MainActivity
            myActivity.onItemClick(it.itemId)
        }
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsListFlow.collect {
                    adapter.submitList(it)

                    delay(1500)
                    swipeContainer.post { swipeContainer.isRefreshing = false }

                    swipeContainer.setOnRefreshListener {
                        viewModel.loadData()
                        swipeContainer.isRefreshing = false
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this).get(NewsListViewModel::class.java)
    }

}