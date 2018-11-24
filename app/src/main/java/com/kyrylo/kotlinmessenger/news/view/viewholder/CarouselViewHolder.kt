package com.kyrylo.kotlinmessenger.news.view.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.kyrylo.kotlinmessenger.R
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ViewListener

class CarouselViewHolder(view: View, var myContext: Context) : RecyclerView.ViewHolder(view) {
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