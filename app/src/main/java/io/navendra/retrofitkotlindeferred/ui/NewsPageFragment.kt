package io.navendra.retrofitkotlindeferred.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import io.navendra.retrofitkotlindeferred.R
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.model.NewsItemFeaturedMedia
import io.navendra.retrofitkotlindeferred.model.NewsItemFeaturedMediaContext

class NewsPageFragment (private val item : NewsItem) : Fragment() {

    companion object {
        fun newInstance() = NewsPageFragment(NewsItem("", NewsItemFeaturedMedia("",
            NewsItemFeaturedMediaContext("")
        ), ""))
    }

    private lateinit var viewModel: SportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadData()

        val pageHeadline: TextView = view.findViewById(R.id.pageHeadline)
        val pageImg: ImageView = view.findViewById(R.id.pageImg)
        val pageText: TextView = view.findViewById(R.id.pageText)

        pageHeadline.text = item.shortHeadline
        Picasso.get().load(item.featuredMedia.featuredMediaContext.featuredMediaContext).into(pageImg)
        pageText.text = item.body

        // (*) Посмотреть ListAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(this).get(SportViewModel::class.java)
    }
}