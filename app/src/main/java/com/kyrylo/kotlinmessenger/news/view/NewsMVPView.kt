package com.kyrylo.kotlinmessenger.news.view

import com.kyrylo.kotlinmessenger.base.view.MVPView
import com.kyrylo.kotlinmessenger.data.preferences.model.GoogleNews

interface NewsMVPView : MVPView {

    fun displayNewsList(blogs: List<GoogleNews>?) : Unit?

}