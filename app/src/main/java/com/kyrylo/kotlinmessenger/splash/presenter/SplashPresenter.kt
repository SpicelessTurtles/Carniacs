package com.kyrylo.kotlinmessenger.splash.presenter

import com.kyrylo.kotlinmessenger.splash.interactor.SplashMVPInteractor
import com.kyrylo.kotlinmessenger.splash.view.SplashMVPView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import com.kyrylo.kotlinmessenger.base.presenter.BasePresenter
import com.kyrylo.kotlinmessenger.utilities.SchedulerProvider

/**
 * Created by jyotidubey on 04/01/18.
 */
class SplashPresenter<V : SplashMVPView, I : SplashMVPInteractor> @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable), SplashMVPPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        decideActivityToOpen()
    }

    private fun decideActivityToOpen() = getView()?.let {
        Thread(Runnable {
            it.logoAnimation()
            for(i in 1..100){
            try {
                Thread.sleep(30)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
           /* if (isUserLoggedIn())
                it.openMainActivity()
            else*/
                it.openLoginActivity()
        }).start()
    }

    private fun isUserLoggedIn(): Boolean {
        interactor?.let { return it.isUserLoggedIn() }
        return false
    }

}