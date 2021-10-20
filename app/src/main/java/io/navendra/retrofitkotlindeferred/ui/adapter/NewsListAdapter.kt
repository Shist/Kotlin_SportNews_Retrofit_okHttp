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

    class ItemViewHolder: RecyclerView.ViewHolder {

        val image: ImageView
        val headline: TextView
        val altText: TextView

        constructor(oddBinding: NewsItemOddBinding): super(oddBinding.root) {
            image = oddBinding.img
            headline = oddBinding.headline
            altText = oddBinding.altText
        }

        constructor(evenBinding: NewsItemEvenBinding): super(evenBinding.root) {
            image = evenBinding.img
            headline = evenBinding.headline
            altText = evenBinding.altText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_ODD -> ItemViewHolder(NewsItemOddBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
        TYPE_EVEN -> ItemViewHolder(NewsItemEvenBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
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

        Picasso.get().load(listItem.context).into(holder.image)
        holder.headline.text = listItem.shortHeadline
        holder.altText.text = listItem.altText
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