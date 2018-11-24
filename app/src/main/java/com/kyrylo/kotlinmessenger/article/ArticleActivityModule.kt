package com.kyrylo.kotlinmessenger.article

import com.kyrylo.kotlinmessenger.article.interactor.ArticleInteractor
import com.kyrylo.kotlinmessenger.article.interactor.ArticleMVPInteractor
import com.kyrylo.kotlinmessenger.article.presenter.ArticleMVPPresenter
import com.kyrylo.kotlinmessenger.article.presenter.ArticlePresenter
import com.kyrylo.kotlinmessenger.article.view.ArticleMVPView

import dagger.Module
import dagger.Provides

@Module
class ArticleActivityModule {

    @Provides
    internal fun provideArticleInteractor(interactor: ArticleInteractor): ArticleMVPInteractor = interactor

    @Provides
    internal fun provideArticlePresenter(presenter: ArticlePresenter<ArticleMVPView, ArticleMVPInteractor>)
            : ArticleMVPPresenter<ArticleMVPView, ArticleMVPInteractor> = presenter
}