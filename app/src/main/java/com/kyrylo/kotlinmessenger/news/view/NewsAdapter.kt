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

    var myContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        myContext = parent.context
        return if (viewType == CAROUSEL_ITEM) {
            val normalView = LayoutInflater.from(parent?.context).inflate(R.layout.carousel_item_list, parent, false)
            CarouselViewHolder(normalView) // view holder for normal items
        } else {
            val headerRow = LayoutInflater.from(parent?.context).inflate(R.layout.news_item_list, parent, false)
            NewsViewHolder(headerRow) // view holder for header items
        }
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

    inner class CarouselViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var customCarouselView: CarouselView? = null
        var testImages: IntArray = intArrayOf(R.drawable.carousel_slide_2, R.drawable.carousel_slide_3)

        fun onBind(position: Int) {
            customCarouselView = itemView.findViewById(R.id.carouselView)

            customCarouselView?.pageCount = 2
            customCarouselView?.setIndicatorVisibility(View.GONE)


            val viewListener = ViewListener { position ->
                val li = myContext?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val customView = li.inflate(R.layout.view_custom, null)
                val labelTextView = customView.findViewById<View>(R.id.labelTextView) as TextView
                val fruitImageView = customView.findViewById<View>(R.id.fruitImageView) as ImageView

                fruitImageView.setImageResource(testImages[position])

                customCarouselView?.indicatorGravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM

                customView
            }

            customCarouselView?.setViewListener(viewListener)
        }
    }

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun clear() {
            itemView.coverImageView.setImageDrawable(null)
            itemView.titleTextView.text = ""
            itemView.contentTextView.text = ""
        }

        fun onBind(position: Int) {

            val (title, url, description, backgroundUrl) = newsListItems[position] as GoogleNews

            inflateData(title, url, "", description, backgroundUrl)
            setItemClickListener("")
        }

        private fun setItemClickListener(blogUrl: String?) {
            itemView.setOnClickListener {
                blogUrl?.let {
                    try {
                        val intent = Intent()
                        // using "with" as an example
                        with(intent) {
                            action = Intent.ACTION_VIEW
                            data = Uri.parse(it)
                            addCategory(Intent.CATEGORY_BROWSABLE)
                        }
                        itemView.context.startActivity(intent)
                    } catch (e: Exception) {
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

    override fun getItemViewType(position: Int): Int {
        return when (newsListItems[position]) {
            is GoogleNews -> NEWS_ITEM
            else -> CAROUSEL_ITEM
        }
    }
}
