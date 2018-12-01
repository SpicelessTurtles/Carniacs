package com.kyrylo.kotlinmessenger.news.presenter

import com.kyrylo.kotlinmessenger.base.presenter.MVPPresenter
import com.kyrylo.kotlinmessenger.data.preferences.model.GoogleNews
import com.kyrylo.kotlinmessenger.news.interactor.NewsMVPInteractor
import com.kyrylo.kotlinmessenger.news.view.NewsMVPView

interface NewsMVPPresenter<V : NewsMVPView, I : NewsMVPInteractor> : MVPPresenter<V, I> {
    fun onViewPrepared()
    fun insertChoosenArticleToDatabase(choosenArticle : GoogleNews)
}