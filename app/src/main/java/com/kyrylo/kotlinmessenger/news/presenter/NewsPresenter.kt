package com.kyrylo.kotlinmessenger.news.presenter

import android.util.Log
import com.kyrylo.kotlinmessenger.base.presenter.BasePresenter
import com.kyrylo.kotlinmessenger.data.preferences.model.GoogleNews
import com.kyrylo.kotlinmessenger.database.dao.TaskDAO
import com.kyrylo.kotlinmessenger.database.entities.Task
import com.kyrylo.kotlinmessenger.news.interactor.NewsMVPInteractor
import com.kyrylo.kotlinmessenger.news.view.NewsMVPView
import com.kyrylo.kotlinmessenger.utilities.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


/**
 * Created by jyotidubey on 13/01/18.
 */
class NewsPresenter<V : NewsMVPView, I : NewsMVPInteractor> @Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable, val taskDao :TaskDAO) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable), NewsMVPPresenter<V, I> {

    override fun insertChoosenArticleToDatabase(choosenArticle : GoogleNews){

        with(choosenArticle) {
            val task = Task(backgroudnImageUrl!!,title!!,description!!,content!!)
            taskDao.insertArticle(task)
        }
    }

    override fun onViewPrepared() {
        getView()?.showProgress()

        interactor?.let {
            it.getNewsList()
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .subscribe { blogResponse ->
                        getView()?.let {
                            it.displayNewsList(blogResponse.data)
                            it.hideProgress()
                        }
                    }
        }
    }


}