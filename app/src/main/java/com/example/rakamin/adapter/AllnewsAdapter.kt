package com.example.rakamin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rakamin.R
import com.example.rakamin.model.ArticleModel
import com.example.rakamin.utils.Utils.DateFormat
import com.example.rakamin.utils.Utils.DateTimeHourAgo
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_semua_berita.view.*
import java.text.DateFormat

class AllnewsAdapter(private val Articlemodel: MutableList<ArticleModel>, private val context: Context) :
    RecyclerView.Adapter<AllnewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_semua_berita, parent, false)
        return AllnewsAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = Articlemodel[position]

        if (model.urlToImage == null){
            holder.imagethumbnail.setImageResource(R.drawable.img)
        } else {
            Picasso.get()
                .load(model.urlToImage)
                .into(holder.imagethumbnail)
        }

        if (model.author == null) {
            holder.tvnamasumber.text = model.sourcemodel?.name
        } else {
            holder.tvnamasumber.text = model.author + " \u2023 " + model.sourcemodel?.name
        }

        holder.tvjudul.text = model.title
        holder.tvtimeago.text = DateTimeHourAgo(model.publishedAt)
        holder.tvdatetime.text = DateFormat(model.publishedAt)
        holder.frameListnews.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return Articlemodel.size
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var frameListnews: FrameLayout
        var tvtimeago: TextView
        var tvnamasumber: TextView
        var tvjudul: TextView
        var tvdatetime: TextView
        var imagethumbnail: RoundedImageView

        init {
            frameListnews = itemView.list_itemnews
            tvtimeago = itemView.tvtimeago
            tvnamasumber = itemView.tvnamasumber
            tvjudul = itemView.tvjudul
            tvdatetime = itemView.tvdatetime
            imagethumbnail = itemView.imgtumbnail
        }
    }

}