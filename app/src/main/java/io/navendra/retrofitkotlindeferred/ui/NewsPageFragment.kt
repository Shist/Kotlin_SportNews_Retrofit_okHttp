package io.navendra.retrofitkotlindeferred.ui

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import io.navendra.retrofitkotlindeferred.databinding.NewsPageBinding
import io.navendra.retrofitkotlindeferred.model.NewsItem

class NewsPageFragment (private val item : NewsItem) : Fragment() {

    companion object {
        lateinit var data : NewsItem
    }

    constructor() : this(data)

    private lateinit var viewModel: SportViewModel

    private var _binding: NewsPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        data = item
        _binding = NewsPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadData()

        val pageHeadline: TextView = binding.pageHeadline
        val pageImg: ImageView = binding.pageImg
        val pageText: TextView = binding.pageText

        pageHeadline.text = item.shortHeadline
        Picasso.get().load(item.featuredMedia.featuredMediaContext.featuredMediaContext).into(pageImg)
        pageText.text = Html.fromHtml(item.body, Html.FROM_HTML_MODE_LEGACY).toString()

        // (*) Посмотреть ListAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(this).get(SportViewModel::class.java)
    }
}