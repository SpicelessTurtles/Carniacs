package com.kyrylo.kotlinmessenger.article.view

import com.kyrylo.kotlinmessenger.base.view.MVPView
import com.kyrylo.kotlinmessenger.database.entities.Task

interface ArticleMVPView : MVPView{
     fun initializeArticle(article : Task)
     fun setUp()
}