package com.kyrylo.kotlinmessenger.news.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.data.preferences.model.GoogleNews
import com.kyrylo.kotlinmessenger.data.preferences.model.ViewHolderItem
import com.kyrylo.kotlinmessenger.utilities.extension.loadImage
import kotlinx.android.synthetic.main.news_item_list.view.*
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.kyrylo.kotlinmessenger.news.view.listener.onArticleClickListener
import com.kyrylo.kotlinmessenger.news.view.viewholder.CarouselViewHolder
import com.kyrylo.kotlinmessenger.news.view.viewholder.NewsViewHolder
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ViewListener


/**
 * Created by jyotidubey on 14/01/18.
 */
class NewsAdapter(private val newsListItems: MutableList<ViewHolderItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        internal val CAROUSEL_ITEM = 0
        internal val NEWS_ITEM = 1
    }

    var myContext: Context? = null//TODO:inject

    private lateinit var articleClickListener: onArticleClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        myContext = parent.context
        return if (viewType == CAROUSEL_ITEM) {
            val normalView = LayoutInflater.from(parent.context).inflate(R.layout.carousel_item_list, parent, false)
            CarouselViewHolder(normalView, myContext!!) // view holder for normal items
        } else {
            val headerRow = LayoutInflater.from(parent.context).inflate(R.layout.news_item_list, parent, false)
            NewsViewHolder(headerRow,newsListItems, articleClickListener) // view holder for header items
        }
    }

    fun setOnArticleClickListener(onClickListener: onArticleClickListener){
        articleClickListener = onClickListener
    }

    override fun getItemCount() = this.newsListItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = holder.let {

        val itemType = getItemViewType(position)

        if (itemType == CAROUSEL_ITEM) {
            (holder as CarouselViewHolder).onBind(position)
        } else if (itemType == NEWS_ITEM) {
            with(it as NewsViewHolder) {
                clear()
                onBind(position)
            }
        }


    }

    internal fun addNewsToList(blogs: List<GoogleNews>, carouselView: CustomCarouselView) {
        this.newsListItems.add(carouselView)
        this.newsListItems.addAll(blogs)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (newsListItems[position]) {
            is GoogleNews -> NewsAdapter.NEWS_ITEM
            else -> NewsAdapter.CAROUSEL_ITEM
        }
    }
}
