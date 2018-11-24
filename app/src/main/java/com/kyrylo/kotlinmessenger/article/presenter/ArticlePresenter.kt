package com.kyrylo.kotlinmessenger.article.presenter

import com.kyrylo.kotlinmessenger.article.interactor.ArticleMVPInteractor
import com.kyrylo.kotlinmessenger.article.view.ArticleMVPView
import com.kyrylo.kotlinmessenger.base.presenter.BasePresenter
import com.kyrylo.kotlinmessenger.database.dao.TaskDAO
import com.kyrylo.kotlinmessenger.utilities.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class ArticlePresenter<V : ArticleMVPView, I : ArticleMVPInteractor> @Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable, val taskDao: TaskDAO) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable), ArticleMVPPresenter<V, I> {

    override fun onViewPrepared() {
        getView()?.initializeArticle(taskDao.getAllArticles()[0])
        getView()?.setUp()

        taskDao.deleteArticle(taskDao.getAllArticles()[0])
    }

}