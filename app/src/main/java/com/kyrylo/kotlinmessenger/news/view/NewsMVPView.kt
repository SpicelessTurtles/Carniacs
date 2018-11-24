package com.kyrylo.kotlinmessenger.news.view

import com.kyrylo.kotlinmessenger.base.view.MVPView
import com.kyrylo.kotlinmessenger.data.preferences.model.GoogleNews
import com.kyrylo.kotlinmessenger.news.view.listener.onArticleClickListener

interface NewsMVPView : MVPView, onArticleClickListener {

    fun displayNewsList(blogs: List<GoogleNews>?) : Unit?

}