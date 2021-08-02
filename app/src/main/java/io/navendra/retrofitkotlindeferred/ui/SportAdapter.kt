package io.navendra.retrofitkotlindeferred.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.databinding.NewsItemBinding

class SportAdapter(private val clickListener: (NewsItem)-> Unit) :
    ListAdapter<NewsItem, SportAdapter.MyViewHolder>(SportNewsDiffCallback()) {

    private var items : List<NewsItem> = emptyList()

    class MyViewHolder(itemBinding: NewsItemBinding): RecyclerView.ViewHolder(itemBinding.root){
        val image: ImageView = itemBinding.img
        val headline: TextView = itemBinding.headline
        val altText: TextView = itemBinding.altText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = getItem(position)

        holder.itemView.setOnClickListener{
            clickListener(listItem)
        }

        Picasso.get().load(listItem.featuredMedia.featuredMediaContext.featuredMediaContext).into(holder.image)
        holder.headline.text = listItem.shortHeadline
        holder.altText.text = listItem.featuredMedia.featuredMediaAltText

    }

    fun setItems(newData: List<NewsItem>) {
        items = newData
    }

}

class SportNewsDiffCallback : DiffUtil.ItemCallback<NewsItem>() {
    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem == newItem
    }
}