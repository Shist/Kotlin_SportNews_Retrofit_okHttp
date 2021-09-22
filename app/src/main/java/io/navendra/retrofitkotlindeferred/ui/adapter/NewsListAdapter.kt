package io.navendra.retrofitkotlindeferred.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.navendra.retrofitkotlindeferred.databinding.NewsItemBinding
import io.navendra.retrofitkotlindeferred.roomDB.entities.NewsItemDB

class SportAdapter(private val clickListener: (NewsItemDB)-> Unit) :
    ListAdapter<NewsItemDB, SportAdapter.MyViewHolder>(SportNewsDiffCallback()) {

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

        Picasso.get().load(listItem.context).into(holder.image)
        holder.headline.text = listItem.shortHeadline
        holder.altText.text = listItem.altText
    }

}

class SportNewsDiffCallback : DiffUtil.ItemCallback<NewsItemDB>() {
    override fun areItemsTheSame(oldItem: NewsItemDB, newItem: NewsItemDB): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: NewsItemDB, newItem: NewsItemDB): Boolean {
        return oldItem == newItem
    }
}