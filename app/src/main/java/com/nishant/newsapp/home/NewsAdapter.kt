package com.nishant.newsapp.home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nishant.newsapp.R
import com.nishant.newsapp.getDisplayDate
import com.nishant.newsapp.model.Articles
import com.nishant.newsapp.nonEmptyStringOrNull
import kotlinx.android.synthetic.main.vh_article.view.*

class NewsAdapter(private var list: List<Articles>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.vh_article, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun updateData(newList: List<Articles>) {
        list = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                list.getOrNull(adapterPosition)?.let { article ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                    it.context.startActivity(intent)
                }
            }
        }

        fun bind(position: Int) {
            list.getOrNull(position)?.let { article ->
                view.txt_title.text = article.title
                view.txt_author.text = article.author
                view.txt_description.text = article.description
                view.txt_publish_date.text =
                    getDisplayDate(article.publishedAt).nonEmptyStringOrNull() ?: ""

                Glide.with(view.image).load(article.urlToImage).into(view.image)
            }
        }
    }
}