package io.navendra.retrofitkotlindeferred.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.navendra.retrofitkotlindeferred.R
import io.navendra.retrofitkotlindeferred.databinding.NewsItemsListBinding
import io.navendra.retrofitkotlindeferred.ui.MainActivity
import io.navendra.retrofitkotlindeferred.ui.adapter.SportAdapter
import io.navendra.retrofitkotlindeferred.ui.repository.LoadState
import io.navendra.retrofitkotlindeferred.ui.viewModel.NewsListViewModel
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

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = SportAdapter {
            val myActivity = requireActivity() as MainActivity
            myActivity.onItemClick(it.itemId)
        }
        recyclerView.adapter = adapter

        swipeContainer.setOnRefreshListener {
            viewModel.loadData()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsListFlow.collect {
                    adapter.submitList(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        LoadState.LOADING -> {
                            swipeContainer.isRefreshing = true
                            Log.d("MyLog", "Got LOADING")
                        }
                        LoadState.SUCCESS -> {
                            swipeContainer.isRefreshing = false
                            Log.d("MyLog", "Got SUCCESS")
                        }
                        LoadState.ERROR -> {
                            swipeContainer.isRefreshing = false
                            val snackbar = Snackbar.make(
                                binding.swipeContainer,
                                R.string.errorText,
                                Snackbar.LENGTH_LONG
                            )
                            snackbar.setAction(R.string.reload, SnackbarAction(viewModel))
                            snackbar.show()
                            //some error handler here...
                            Log.d("MyLog", "Got ERROR")
                        }
                        LoadState.IDLE -> {
                            Log.d("MyLog", "Something get wrong: IDLE state after LoadData() . . .")
                        }
                    }
                }
            }
        }

        if (savedInstanceState == null && viewModel.state.value == LoadState.IDLE) {
            viewModel.loadData()
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