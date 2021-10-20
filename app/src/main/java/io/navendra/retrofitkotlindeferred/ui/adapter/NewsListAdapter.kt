package io.navendra.retrofitkotlindeferred.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.navendra.retrofitkotlindeferred.databinding.NewsItemOddBinding
import io.navendra.retrofitkotlindeferred.databinding.NewsItemEvenBinding
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemTable

// Мы не можем возвращать две штуки сразу, но нам нужны обе
class SportAdapter(private val clickListener: (NewsItemTable)-> Unit) :
    ListAdapter<NewsItemTable, SportAdapter.OddViewHolder>(SportNewsDiffCallback()),
    ListAdapter<NewsItemTable, SportAdapter.EvenViewHolder>(SportNewsDiffCallback()) {

    companion object {
        private const val TYPE_ODD = 1
        private const val TYPE_EVEN = 2
    }

    class OddViewHolder(oddItemBinding: NewsItemOddBinding): RecyclerView.ViewHolder(oddItemBinding.root) {
        val image: ImageView = oddItemBinding.img
        val headline: TextView = oddItemBinding.headline
        val altText: TextView = oddItemBinding.altText
    }

    class EvenViewHolder(evenItemBinding: NewsItemEvenBinding): RecyclerView.ViewHolder(evenItemBinding.root) {
        val image: ImageView = evenItemBinding.img
        val headline: TextView = evenItemBinding.headline
        val altText: TextView = evenItemBinding.altText
    }

    // Проблема связана с верхней проблемой: мы не знаем, какой из двух возвращаем и соответственно какой нужен
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_ODD -> NewsItemOddBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        TYPE_EVEN -> NewsItemEvenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        else -> throw IllegalArgumentException()
    }

    override fun getItemViewType(position: Int): Int =
        when (position % 2) {
            1 -> TYPE_ODD
            2 -> TYPE_EVEN
            else -> throw IllegalArgumentException()
        }

    override fun onBindViewHolder(oddHolder: OddViewHolder,
                                  evenHolder: EvenViewHolder,
                                  position: Int) {
        val listItem = getItem(position) // Опять таки, он не понимает, который айтем получать из двух

        when (getItemViewType(position)) {
            TYPE_ODD -> {
                oddHolder.itemView.setOnClickListener {
                    clickListener(listItem)
                }
                Picasso.get().load(listItem.context).into(oddHolder.image)
                oddHolder.headline.text = listItem.shortHeadline
                oddHolder.altText.text = listItem.altText
            }
            TYPE_EVEN -> {
                evenHolder.itemView.setOnClickListener {
                    clickListener(listItem)
                }
                Picasso.get().load(listItem.context).into(evenHolder.image)
                evenHolder.headline.text = listItem.shortHeadline
                evenHolder.altText.text = listItem.altText
            }
        }
    }

}

class SportNewsDiffCallback : DiffUtil.ItemCallback<NewsItemTable>() {
    override fun areItemsTheSame(oldItem: NewsItemTable, newItem: NewsItemTable): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: NewsItemTable, newItem: NewsItemTable): Boolean {
        return oldItem == newItem
    }
}