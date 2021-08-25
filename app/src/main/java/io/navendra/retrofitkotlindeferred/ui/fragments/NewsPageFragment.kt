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
import androidx.lifecycle.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Picasso
import io.navendra.retrofitkotlindeferred.databinding.NewsPageBinding
import io.navendra.retrofitkotlindeferred.roomDB.entities.NewsItemDB
import io.navendra.retrofitkotlindeferred.ui.viewModel.NewsPageViewModel
import io.navendra.retrofitkotlindeferred.ui.viewModel.PageViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsPageFragment : Fragment() {

    companion object {
        const val keyItemID = "itemID"
        fun newInstance(itemID: String) = NewsPageFragment().apply {
            arguments = Bundle().apply {
                putString(keyItemID, itemID)
            }
        }
    }

    private lateinit var viewModel: NewsPageViewModel

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

        val itemID = arguments?.getString(keyItemID)

        viewModel.loadData(itemID!!)

        val pageHeadline: TextView = binding.pageHeadline
        val pageImg: ImageView = binding.pageImg
        val pageText: TextView = binding.pageText

        var item: NewsItemDB? = null

        swipeContainer = binding.swipeContainer
        swipeContainer?.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsPageFlow.collect {
                    binding.loadingPanel.visibility = View.GONE
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

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val itemID = arguments?.getString(keyItemID)

        val pageViewModelFactory = PageViewModelFactory(activity?.application!!, itemID!!)
        viewModel = ViewModelProvider(this, pageViewModelFactory).get(NewsPageViewModel::class.java)
    }

}