import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import io.navendra.retrofitkotlindeferred.ui.SportFragment
import com.squareup.picasso.Picasso
import io.navendra.retrofitkotlindeferred.R
import kotlinx.android.synthetic.main.sport_fragment_layout.view.*

class SportAdapter(private val context: Context, private val movieList: MutableList<SportFragment>):RecyclerView.Adapter<SportAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.img
        val headline: TextView = itemView.headline
        val altText: TextView = itemView.altText

        fun bind(listItem: SportFragment) {
            image.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${itemView.img}", Toast.LENGTH_SHORT)
                    .show()
            }
            itemView.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${itemView.headline.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.sport_fragment_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = movieList[position]
        holder.bind(listItem)

        Picasso.get().load(movieList[position].imgUri).into(holder.image)
        holder.headline.text = movieList[position].headline
        holder.altText.text = movieList[position].altText
    }

}