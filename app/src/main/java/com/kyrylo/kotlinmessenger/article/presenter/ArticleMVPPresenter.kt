package com.kyrylo.kotlinmessenger.article.presenter

import com.kyrylo.kotlinmessenger.article.interactor.ArticleMVPInteractor
import com.kyrylo.kotlinmessenger.article.view.ArticleMVPView
import com.kyrylo.kotlinmessenger.base.presenter.MVPPresenter


interface ArticleMVPPresenter<V : ArticleMVPView, I : ArticleMVPInteractor> : MVPPresenter<V, I>{
   fun onViewPrepared()
}