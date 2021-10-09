package io.navendra.retrofitkotlindeferred.ui.fragments

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import io.navendra.retrofitkotlindeferred.databinding.NewsPageBinding
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails.NewsItemDetailsTable
import io.navendra.retrofitkotlindeferred.ui.viewModel.NewsPageViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsPageFragment : Fragment() {

    companion object {
        fun newInstance(itemID: String) = NewsPageFragment().apply {
            arguments = Bundle().apply {
                putString("itemID", itemID)
            }
        }
    }

    val viewModel: NewsPageViewModel by viewModels()

    private var swipeContainer: SwipeRefreshLayout? = null

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

        swipeContainer = binding.swipeContainer
        swipeContainer?.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        val pageHeadline: TextView = binding.pageHeadline
        val pageImg: ImageView = binding.pageImg
        val pageText: TextView = binding.pageText

        var item: NewsItemDetailsTable?

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsPageFlow.collect {
                    item = it

                    pageHeadline.text = item?.shortHeadline
                    Picasso.get().load(item?.context)
                        .into(pageImg)
                    pageText.text = Html.fromHtml(item?.body, Html.FROM_HTML_MODE_LEGACY).toString()

                    swipeContainer?.setOnRefreshListener {
                        viewModel.loadData(itemID)
                        swipeContainer?.isRefreshing = false
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}