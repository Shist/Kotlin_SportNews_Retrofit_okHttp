import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.R
import io.navendra.retrofitkotlindeferred.ui.MainActivity
import io.navendra.retrofitkotlindeferred.ui.SportFragment

class SportAdapter(private val context: Context, private val sportList: List<NewsItem>):RecyclerView.Adapter<SportAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //TODO Можно будет попробовать через ViewBinding
        val image: ImageView = itemView.findViewById(R.id.img)
        val headline: TextView = itemView.findViewById(R.id.headline)
        val altText: TextView = itemView.findViewById(R.id.altText)

        fun bind(listItem: NewsItem) {
            image.setOnClickListener {
                SportAdapter.onItemClick()
            }
            headline.setOnClickListener {
                onItemClick()
            }
        }
    }

    class MyViewHolderPage(itemView: View): RecyclerView.ViewHolder(itemView){
        //TODO Можно будет попробовать через ViewBinding
        val image: ImageView = itemView.findViewById(R.id.img)
        val headline: TextView = itemView.findViewById(R.id.headline)
        //val altText: TextView = itemView.findViewById(R.id.altText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.sport_fragment, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = sportList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = sportList[position]
        holder.bind(listItem)

        fun onItemClick() {
            (context as MainActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, SportFragment())
                .commit()
        }

        Picasso.get().load(sportList[position].featuredMedia.featuredMediaContext.featuredMediaContext).into(holder.image)
        holder.headline.text = sportList[position].shortHeadline
        holder.altText.text = sportList[position].featuredMedia.featuredMediaAltText
    }

}