package io.navendra.retrofitkotlindeferred.ui.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.navendra.retrofitkotlindeferred.databinding.NewsItemsListBinding
import io.navendra.retrofitkotlindeferred.ui.viewModel.LatestNewsUiState
import io.navendra.retrofitkotlindeferred.ui.MainActivity
import io.navendra.retrofitkotlindeferred.ui.viewModel.NewsListViewModel
import io.navendra.retrofitkotlindeferred.ui.adapter.SportAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsListFragment : Fragment() {

    private lateinit var viewModel: NewsListViewModel

    private var swipeContainer: SwipeRefreshLayout? = null

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

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsFlow.collect { uiState ->
                    when (uiState) {
                        is LatestNewsUiState.Success -> {
                            adapter.submitList(viewModel.news)
                            swipeContainer?.isRefreshing = false
                        }
                        is LatestNewsUiState.Error -> uiState.showError(uiState.exception)
                    }
                }
            }

            swipeContainer = binding.swipeContainer
            swipeContainer?.setOnRefreshListener {
                viewModel.loadData()
            }
            swipeContainer?.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(requireActivity()).get(NewsListViewModel::class.java)
    }

}
