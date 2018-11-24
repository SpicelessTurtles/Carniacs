package com.kyrylo.kotlinmessenger.news.view.listener

import com.kyrylo.kotlinmessenger.data.preferences.model.GoogleNews

interface onArticleClickListener {
    fun onArticleClick(article: GoogleNews)
}