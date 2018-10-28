package com.kyrylo.kotlinmessenger.utilities.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by jyotidubey on 24/01/18.
 */
internal fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
            .load(url)
            .asBitmap()
            .centerCrop()
            .into(this)
}