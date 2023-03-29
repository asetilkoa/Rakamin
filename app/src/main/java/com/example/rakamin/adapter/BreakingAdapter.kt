package com.example.rakamin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.rakamin.R
import com.example.rakamin.model.ArticleModel
import com.example.rakamin.utils.Utils.DateFormat
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_berita_terkini.view.*

class BreakingAdapter (private val Articlemodel: MutableList<ArticleModel>, private val context: Context) :
    RecyclerView.Adapter<BreakingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_berita_terkini, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = Articlemodel[position]

        if (model.urlToImage == null){
            holder.imageview.setImageResource(R.drawable.img)
        } else {
            Picasso.get()
                .load(model.urlToImage)
                .into(holder.imageview)
        }

        holder.tvTitle.text = model.title
        holder.tvSumber.text = model.author
        holder.tvTgl.text = DateFormat(model.publishedAt)
    }

    override fun getItemCount(): Int {
        return Articlemodel.size
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var cvbreaking: CardView
        var imageview: RoundedImageView
        var tvTitle: TextView
        var tvSumber: TextView
        var tvTgl: TextView

        init {
            cvbreaking = itemView.cvbreaking
            imageview = itemView.image
            tvTitle = itemView.tvTitle
            tvSumber = itemView.tvSumber
            tvTgl = itemView.tvTgl
        }
    }
}