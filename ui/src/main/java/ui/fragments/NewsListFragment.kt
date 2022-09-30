package ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.view_model.loadState.LoadState
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import ui.adapter.SportAdapter
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ui.MainActivity
import ui.R
import ui.databinding.NewsItemsListBinding

class NewsListFragment : Fragment(), KoinComponent {

    private val viewModel: com.view_model.NewsListViewModel by inject()

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
                            noDataImage.isVisible = true
                            noDataText.isVisible = true
                        }
                    }
                    else {
                        binding.swipeContainer.apply {
                            noDataImage.isInvisible = true
                            noDataText.isInvisible = true
                        }
                        adapter.submitList(it)
                    }
                }
            }
        }

        val timeLength = if (adapter.itemCount == 0) // Если данных вообще нету (даже в базе)
            Snackbar.LENGTH_INDEFINITE
        else // Если новые данные не пришли, но есть старые данные в базе
            Snackbar.LENGTH_LONG

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        LoadState.LOADING -> {
                            swipeContainer.isRefreshing = true
                            createSnackbar(resources.getString(R.string.loading),
                                requireContext().getColor(R.color.colorLoading))
                        }
                        LoadState.SUCCESS -> {
                            swipeContainer.isRefreshing = false
                            createSnackbar(resources.getString(R.string.loadingSuccess),
                                requireContext().getColor(R.color.colorSuccess))
                        }
                        LoadState.INTERNET_ERROR -> {
                            swipeContainer.isRefreshing = false
                            createSnackbarWithReload(timeLength,
                                resources.getString(R.string.errorNetwork))
                        }
                        LoadState.UNKNOWN_ERROR -> {
                            swipeContainer.isRefreshing = false
                            createSnackbarWithReload(timeLength,
                                resources.getString(R.string.errorUnknownNoData))
                        }
                        LoadState.EMPTY_ITEMS_LIST_ERROR -> {
                            swipeContainer.isRefreshing = false
                            createSnackbarWithReload(timeLength,
                                resources.getString(R.string.errorNoNewsOnAPI))
                        }
                        LoadState.EMPTY_ITEMS_DETAILS_LIST_ERROR -> {
                            swipeContainer.isRefreshing = false
                            createSnackbarWithReload(timeLength,
                                resources.getString(R.string.errorNoNewsDetailsOnAPI))
                        }
                        else -> createSnackbar(resources.getString(R.string.launching),
                            requireContext().getColor(R.color.colorLaunching))
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

    private fun createSnackbar(message: String, color: Int) {
        val snackbar = Snackbar.make(
            binding.swipeContainer,
            message,
            LENGTH_SHORT
        )
        snackbar.setTextColor(color)
        snackbar.show()
    }

    private fun createSnackbarWithReload(snackbarTimeLength: Int, messageError: String) {
        val snackbar = Snackbar.make(
            binding.swipeContainer,
            messageError,
            snackbarTimeLength
        )
        snackbar.setTextColor(requireContext().getColor(R.color.colorMistakeText))
        snackbar.setActionTextColor(requireContext().getColor(R.color.colorMistakeReload))
        snackbar.setAction(R.string.reload) {
            viewModel.loadData()
        }
        snackbar.show()
    }

}