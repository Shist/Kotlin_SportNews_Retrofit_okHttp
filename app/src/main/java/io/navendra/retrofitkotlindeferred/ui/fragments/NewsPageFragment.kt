package io.navendra.retrofitkotlindeferred.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.squareup.picasso.Picasso
import io.navendra.retrofitkotlindeferred.databinding.NewsPageBinding
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.ui.viewModel.LatestNewsListUiState
import io.navendra.retrofitkotlindeferred.ui.viewModel.LatestNewsPageUiState
import io.navendra.retrofitkotlindeferred.ui.viewModel.NewsPageViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsPageFragment : Fragment() {

    companion object {
        fun newInstance(itemID: String) = NewsPageFragment().apply {
            arguments = Bundle().apply {
                putString("itemID", itemID)
            }
        }
    }

    private lateinit var viewModel: NewsPageViewModel

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

        val itemID = arguments?.getString("itemID")

        viewModel.loadData(itemID!!)

        val pageHeadline: TextView = binding.pageHeadline
        val pageImg: ImageView = binding.pageImg
        val pageText: TextView = binding.pageText

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsPageFlow.collect { uiState ->
                    when (uiState) {
                        is LatestNewsPageUiState.Success -> {
                            var item = uiState.news_item
                            pageHeadline.text = item?.shortHeadline
                            Picasso.get().load(item?.featuredMedia?.featuredMediaContext?.featuredMediaContext)
                                .into(pageImg)
                            pageText.text = Html.fromHtml(item?.body, Html.FROM_HTML_MODE_LEGACY).toString()
                        }
                        is LatestNewsPageUiState.Loading -> {
                        }
                        is LatestNewsPageUiState.Error -> uiState.showError(uiState.exception)
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

        viewModel = ViewModelProvider(this).get(NewsPageViewModel::class.java)
    }

}