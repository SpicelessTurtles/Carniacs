package com.kyrylo.kotlinmessenger.news.view.viewholder

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.kyrylo.kotlinmessenger.data.preferences.model.GoogleNews
import com.kyrylo.kotlinmessenger.data.preferences.model.ViewHolderItem
import com.kyrylo.kotlinmessenger.main.view.MainActivity
import com.kyrylo.kotlinmessenger.news.view.listener.onArticleClickListener
import com.kyrylo.kotlinmessenger.utilities.extension.loadImage
import kotlinx.android.synthetic.main.news_item_list.view.*

class NewsViewHolder(val view: View, val newsListItems: MutableList<ViewHolderItem>, val onArticleClickListener: onArticleClickListener) : RecyclerView.ViewHolder(view) {

    fun clear() {
        itemView.coverImageView.setImageDrawable(null)
        itemView.titleTextView.text = ""
        itemView.contentTextView.text = ""
    }

    fun onBind(position: Int) {

        val (title, url, description, backgroundUrl) = newsListItems[position] as GoogleNews

        inflateData(title, url, "", description, backgroundUrl)
        setItemClickListener("",position)
    }

    private fun setItemClickListener(blogUrl: String?,position: Int) {

        itemView.setOnClickListener {
            blogUrl?.let {
                try {
                    with(itemView.context as MainActivity){
                        onArticleClickListener.onArticleClick(newsListItems[position] as GoogleNews)
                        presenter.onArticleActivityClick()
                    }
                } catch (e: Exception) {
                   Log.d("exc",e.message)
                }
            }

        }
    }

    private fun inflateData(title: String?, author: String?, date: String?, description: String?, coverPageUrl: String?) {
        title?.let { itemView.titleTextView.text = it }
        //  author?.let { itemView.authorTextView.text = it }
        //  date?.let { itemView.dateTextView.text = it }
        description?.let { itemView.contentTextView.text = it }
        coverPageUrl?.let {
            itemView.coverImageView.loadImage(it)
        }
    }

}
