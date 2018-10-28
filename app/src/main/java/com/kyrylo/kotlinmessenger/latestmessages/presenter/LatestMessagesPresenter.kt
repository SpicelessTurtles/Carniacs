package com.kyrylo.kotlinmessenger.latestmessages.presenter

import com.kyrylo.kotlinmessenger.latestmessages.interactor.LatestMessagesMVPInteractor
import com.kyrylo.kotlinmessenger.latestmessages.view.LatestMessagesMVPView
import io.reactivex.disposables.CompositeDisposable
import com.kyrylo.kotlinmessenger.utilities.SchedulerProvider
import javax.inject.Inject

class LatestMessagesPresenter<V : LatestMessagesMVPView, I : LatestMessagesMVPInteractor>

@Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable): LatestMessagesMVPPresenter<V,I> {

    override fun onAttach(view: V?) {

    }

    override fun onDetach() {

    }

    override fun getView(): V? {
             return null
    }

}