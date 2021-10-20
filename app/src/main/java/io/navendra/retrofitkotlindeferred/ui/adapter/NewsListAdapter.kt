package io.navendra.retrofitkotlindeferred.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.navendra.retrofitkotlindeferred.R
import io.navendra.retrofitkotlindeferred.databinding.NewsItemOddBinding
import io.navendra.retrofitkotlindeferred.databinding.NewsItemEvenBinding
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemTable

class SportAdapter(private val clickListener: (NewsItemTable)-> Unit) :
    ListAdapter<NewsItemTable, RecyclerView.ViewHolder>(SportNewsDiffCallback()) {

    companion object {
        private const val TYPE_ODD = 0
        private const val TYPE_EVEN = 1
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_ODD -> OddViewHolder(NewsItemOddBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
        TYPE_EVEN -> EvenViewHolder(NewsItemEvenBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
        else -> throw IllegalArgumentException()
    }

    override fun getItemViewType(position: Int): Int =
        when (position % 2) {
            0 -> TYPE_ODD
            1 -> TYPE_EVEN
            else -> throw IllegalArgumentException()
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listItem = getItem(position)

        holder.itemView.setOnClickListener {
            clickListener(listItem)
        }

        when (getItemViewType(position)) {
            TYPE_ODD -> {
                Picasso.get().load(listItem.context).into(holder.itemView.findViewById() image)
                holder.itemViewType. .headline.text = listItem.shortHeadline
                oddHolder.altText.text = listItem.altText
            }
            TYPE_EVEN -> {
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