package com.nishant.newsapp.home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nishant.newsapp.R
import com.nishant.newsapp.databinding.VhArticleBinding
import com.nishant.newsapp.getDisplayDate
import com.nishant.newsapp.model.Articles
import com.nishant.newsapp.nonEmptyStringOrNull

class NewsAdapter(private var list: List<Articles>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            VhArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class ViewHolder(val viewBinding: VhArticleBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        init {
            viewBinding.root.setOnClickListener {
                list.getOrNull(adapterPosition)?.let { article ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                    it.context.startActivity(intent)
                }
            }
        }

        fun bind(position: Int) {
            list.getOrNull(position)?.let { article ->
                viewBinding.txtTitle.text = article.title
                viewBinding.txtAuthor.text = article.author
                viewBinding.txtDescription.text = article.description
                viewBinding.txtPublishDate.text =
                    getDisplayDate(article.publishedAt).nonEmptyStringOrNull() ?: ""

                Glide.with(viewBinding.image).load(article.urlToImage).into(viewBinding.image)
            }
        }
    }
}