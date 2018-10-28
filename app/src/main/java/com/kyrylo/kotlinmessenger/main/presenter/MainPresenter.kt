package com.kyrylo.kotlinmessenger.main.presenter

import com.kyrylo.kotlinmessenger.base.presenter.BasePresenter
import com.kyrylo.kotlinmessenger.main.interactor.MainMVPInteractor
import com.kyrylo.kotlinmessenger.main.view.MainMVPView
import com.kyrylo.kotlinmessenger.utilities.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by jyotidubey on 08/01/18.
 */
class MainPresenter<V : MainMVPView, I : MainMVPInteractor>
@Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable), MainMVPPresenter<V, I> {


    override fun onAttach(view: V?) {
        super.onAttach(view)
        getUserData()
        getView()?.openNewsFragment()
    }

  //  override fun refreshQuestionCards() = getQuestionCards()

   // override fun onDrawerOptionRateUsClick() = getView()?.openRateUsDialog()

    override fun onDrawerOptionFeedClick() = getView()?.openFeedActivity()

    override fun onDrawerOptionAboutClick() = getView()?.openNewsFragment()

    override fun onDrawerOptionLogoutClick() {
        /* getView()?.showProgress()
         interactor?.let {
             compositeDisposable.add(
                     it.makeLogoutApiCall()
                             .compose(schedulerProvider.ioToMainObservableScheduler())
                             .subscribe({
                                 interactor?.performUserLogout()
                                 getView()?.let {
                                     it.hideProgress()
                                     it.openLoginActivity()
                                 }
                             }, { err -> println(err) }))
         }*/

    }




    private fun getUserData() = interactor?.let {
    //    val userData = it.getUserDetails()
      //  getView()?.inflateUserDetails(userData)
    }

}