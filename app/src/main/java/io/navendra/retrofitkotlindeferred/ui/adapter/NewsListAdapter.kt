package io.navendra.retrofitkotlindeferred.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.squareup.picasso.Picasso
import io.navendra.retrofitkotlindeferred.databinding.NewsItemOddBinding
import io.navendra.retrofitkotlindeferred.databinding.NewsItemEvenBinding
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemTable

class SportAdapter(private val clickListener: (NewsItemTable)-> Unit) :
    ListAdapter<NewsItemTable, SportAdapter.ItemViewHolder>(SportNewsDiffCallback()) {

    companion object {
        private const val TYPE_ODD = 0
        private const val TYPE_EVEN = 1
    }

    class ItemViewHolder(bindType: ViewBinding): RecyclerView.ViewHolder(bindType.root) {

        lateinit var image: ImageView
        lateinit var headline: TextView
        lateinit var altText: TextView

        constructor(bindType: ViewBinding, typeNum: Int) : this(bindType) {
            when (typeNum) {
                0 -> {
                    bindType as NewsItemOddBinding
                    image = bindType.img
                    headline = bindType.headline
                    altText = bindType.altText
                }
                1 -> {
                    bindType as NewsItemEvenBinding
                    image = bindType.img
                    headline = bindType.headline
                    altText = bindType.altText
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_ODD -> ItemViewHolder(NewsItemOddBinding
            .inflate(LayoutInflater.from(parent.context), parent, false), 0)
        TYPE_EVEN -> ItemViewHolder(NewsItemEvenBinding
            .inflate(LayoutInflater.from(parent.context), parent, false), 1)
        else -> throw IllegalArgumentException()
    }

    override fun getItemViewType(position: Int): Int =
        when (position % 2) {
            0 -> TYPE_ODD
            1 -> TYPE_EVEN
            else -> throw IllegalArgumentException()
        }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val listItem = getItem(position)

        holder.itemView.setOnClickListener {
            clickListener(listItem)
        }

        when (holder.itemViewType) {
            TYPE_ODD -> {
                Picasso.get().load(listItem.context).into(holder.image)
                holder.headline.text = listItem.shortHeadline
                holder.altText.text = listItem.altText
            }
            TYPE_EVEN -> {
                Picasso.get().load(listItem.context).into(holder.image)
                holder.headline.text = listItem.shortHeadline
                holder.altText.text = listItem.altText
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