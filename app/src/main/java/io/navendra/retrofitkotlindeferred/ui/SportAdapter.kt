import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.R

class SportAdapter(private val clickListener: (NewsItem)-> Unit):RecyclerView.Adapter<SportAdapter.MyViewHolder>() {

    private var items : List<NewsItem>? = null

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //TODO Можно будет попробовать через ViewBinding
        val image: ImageView = itemView.findViewById(R.id.img)
        val headline: TextView = itemView.findViewById(R.id.headline)
        val altText: TextView = itemView.findViewById(R.id.altText)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = items!!.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = items!![position]

        holder.itemView.setOnClickListener{
            clickListener(listItem)
        }

        Picasso.get().load(listItem.featuredMedia.featuredMediaContext.featuredMediaContext).into(holder.image)
        holder.headline.text = listItem.shortHeadline
        holder.altText.text = listItem.featuredMedia.featuredMediaAltText

    }

    fun setItems(newData: MutableLiveData<List<NewsItem>>) {
        items = newData.value
    }

}