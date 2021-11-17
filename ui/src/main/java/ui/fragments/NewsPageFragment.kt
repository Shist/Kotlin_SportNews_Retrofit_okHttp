package ui.fragments

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import domain.NewsItemDetails
import ui.viewModel.LoadState
import ui.viewModel.NewsPageViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import ui.R
import ui.databinding.NewsPageBinding

class NewsPageFragment : Fragment(), KoinComponent {

    companion object {
        const val keyItemID = "itemID"
        fun newInstance(itemID: String) = NewsPageFragment().apply {
            arguments = Bundle().apply {
                putString(keyItemID, itemID)
            }
        }
    }

    private val viewModel: NewsPageViewModel by inject {
        parametersOf(arguments?.getString(keyItemID))
    }

    private var _binding: NewsPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewsPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemID = arguments?.getString(keyItemID)
        viewModel.loadData(itemID!!)

        val swipeContainer = binding.swipeContainer
        swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        val pageHeadline: TextView = binding.pageHeadline
        val pageImg: ImageView = binding.pageImg
        val pageText: TextView = binding.pageText

        var item: NewsItemDetails?

        swipeContainer.setOnRefreshListener {
            viewModel.loadData(itemID)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsPageFlow.collect {
                    item = it

                    pageHeadline.text = item?.shortHeadline
                    Picasso.get().load(item?.context)
                        .into(pageImg)
                    pageText.text = Html.fromHtml(item?.body, Html.FROM_HTML_MODE_LEGACY).toString()
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        LoadState.LOADING -> {
                            swipeContainer.isRefreshing = true
                            createSnackbar(resources.getString(R.string.loadingItem),
                                requireContext().getColor(R.color.colorLoading))
                        }
                        LoadState.SUCCESS -> {
                            swipeContainer.isRefreshing = false
                            createSnackbar(resources.getString(R.string.loadingItemSuccess),
                                requireContext().getColor(R.color.colorSuccess))
                        }
                        LoadState.INTERNET_ERROR -> {
                            swipeContainer.isRefreshing = false
                            createSnackbarWithReload(resources.getString(R.string.errorNetwork))
                        }
                        LoadState.UNKNOWN_ERROR -> {
                            swipeContainer.isRefreshing = false
                            createSnackbarWithReload(resources.getString(R.string.errorUnknownNoData))
                        }
                        LoadState.EMPTY_ITEMS_DETAILS_LIST_ERROR -> {
                            swipeContainer.isRefreshing = false
                            createSnackbarWithReload(resources.getString(R.string.errorNoNewsDetailsOnAPI))
                        }
                        LoadState.INCONSISTENCY_ITEM_ID_ERROR -> {
                            swipeContainer.isRefreshing = false
                            createSnackbarWithReload(resources.getString(R.string.errorNoSuchItem))
                        }
                        else -> createSnackbar(resources.getString(R.string.launchingItem),
                            requireContext().getColor(R.color.colorLaunching))
                    }
                }
            }
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

    private fun createSnackbarWithReload(messageError: String) {
        val snackbar = Snackbar.make(
            binding.swipeContainer,
            messageError,
            LENGTH_LONG
        )
        snackbar.setTextColor(requireContext().getColor(R.color.colorMistakeText))
        snackbar.setActionTextColor(requireContext().getColor(R.color.colorMistakeReload))
        snackbar.setAction(R.string.reload) {
            viewModel.loadData(keyItemID)
        }
        snackbar.show()
    }

}