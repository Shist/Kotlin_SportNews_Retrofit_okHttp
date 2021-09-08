package io.navendra.retrofitkotlindeferred.ui.fragments

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import io.navendra.retrofitkotlindeferred.R
import io.navendra.retrofitkotlindeferred.databinding.NewsItemsListBinding
import io.navendra.retrofitkotlindeferred.ui.MainActivity
import io.navendra.retrofitkotlindeferred.ui.adapter.SportAdapter
import io.navendra.retrofitkotlindeferred.ui.repository.LoadState
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noDataImage = binding.noDataImage
        val noDataText = binding.noDataText

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
                    if (it.isEmpty()) {
                        binding.swipeContainer.apply {
                            Picasso.get().load(R.drawable.no_data_yet).into(noDataImage)
                            noDataText.text = resources.getString(R.string.noDataYet);
                        }
                    }
                    else {
                        noDataImage.setImageResource(0)
                        noDataText.text = null
                        adapter.submitList(it)
                    }
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
                        LoadState.ERROR_WITH_DATA -> {
                            swipeContainer.isRefreshing = false
                            createSnackbar(Snackbar.LENGTH_LONG)
                            Log.d("MyLog", "Got ERROR WITH DATA")
                        }
                        LoadState.ERROR_NO_DATA -> {
                            swipeContainer.isRefreshing = false
                            createSnackbar(Snackbar.LENGTH_INDEFINITE)
                            Log.d("MyLog", "Got ERROR WITHOUT ANY DATA")
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

    private fun createSnackbar(snackbarTimeLength : Int) {
        val snackbar = Snackbar.make(
            binding.swipeContainer,
            R.string.errorText,
            snackbarTimeLength
        )
        snackbar.setActionTextColor(Color.parseColor("#00a390"))
        snackbar.setAction(R.string.reload, SnackbarAction(viewModel))
        snackbar.show()
    }

}