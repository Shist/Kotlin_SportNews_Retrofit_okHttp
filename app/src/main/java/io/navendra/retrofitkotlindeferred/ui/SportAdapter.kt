import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.R
import io.navendra.retrofitkotlindeferred.ui.MainActivity
import io.navendra.retrofitkotlindeferred.ui.SportFragment

class SportAdapter(private val sportList: List<NewsItem>,
                   private val clickListener: (NewsItem)-> Unit):RecyclerView.Adapter<SportAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //TODO Можно будет попробовать через ViewBinding
        val image: ImageView = itemView.findViewById(R.id.img)
        val headline: TextView = itemView.findViewById(R.id.headline)
        val altText: TextView = itemView.findViewById(R.id.altText)

        // Вот это в другом фрагменте нужно
        val pageText: TextView = itemView.findViewById(R.id.pageText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.sport_fragment, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = sportList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = sportList[position]

        holder.itemView.setOnClickListener{
            clickListener(listItem)
        }

        Picasso.get().load(listItem.featuredMedia.featuredMediaContext.featuredMediaContext).into(holder.image)
        holder.headline.text = listItem.shortHeadline
        holder.altText.text = listItem.featuredMedia.featuredMediaAltText

        // Вот это в другом фрагменте нужно
        holder.pageText.text = listItem.body
    }

}