package iteso.mx.itesogram.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.parse.ParseObject
import iteso.mx.itesogram.R
import com.bumptech.glide.Glide
import com.parse.ParseFile

class AdapterPhoto(var users : List<ParseObject>): RecyclerView.Adapter<AdapterPhoto.RecyclerHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.feed, parent, false)
        return RecyclerHolder(v)
    }

    override fun getItemCount(): Int {return users.size}

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.bind(users[position])
    }

    class RecyclerHolder(view: View): RecyclerView.ViewHolder(view){
        private var rm = Glide.with(view)

        fun bind(item: ParseObject){
            val username_tv: TextView = itemView.findViewById(R.id.tv_username)
            username_tv.text = item.getString("username").toString()

            val image_iv: ImageView = itemView.findViewById(R.id.image)

            val imgage_pf: ParseFile = item.getParseFile("photo")!!
            rm.load(imgage_pf.url).into(image_iv)

            val comment_tv: TextView = itemView.findViewById(R.id.tv_total_comment)
            comment_tv.text = item.get("commentsNumber").toString()
        }
    }
}