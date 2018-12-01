package com.kyrylo.kotlinmessenger.profile.presenter

import com.kyrylo.kotlinmessenger.base.presenter.BasePresenter
import com.kyrylo.kotlinmessenger.database.dao.TaskDAO
import com.kyrylo.kotlinmessenger.news.interactor.NewsMVPInteractor
import com.kyrylo.kotlinmessenger.news.presenter.NewsMVPPresenter
import com.kyrylo.kotlinmessenger.news.view.NewsMVPView
import com.kyrylo.kotlinmessenger.profile.interactor.ProfileMVPInteractor
import com.kyrylo.kotlinmessenger.profile.view.ProfileMVPView
import com.kyrylo.kotlinmessenger.utilities.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ProfilePresenter <V : ProfileMVPView, I : ProfileMVPInteractor> @Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable, val taskDao : TaskDAO) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable), ProfileMVPPresenter<V, I> {

}