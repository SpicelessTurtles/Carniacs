package com.kyrylo.kotlinmessenger.users.presenter

import com.kyrylo.kotlinmessenger.base.presenter.BasePresenter
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import com.kyrylo.kotlinmessenger.users.view.viewholder.UserItem
import com.kyrylo.kotlinmessenger.users.interactor.UsersMVPInteractor
import com.kyrylo.kotlinmessenger.users.view.UsersMVPView
import io.reactivex.disposables.CompositeDisposable
import com.kyrylo.kotlinmessenger.utilities.SchedulerProvider

import javax.inject.Inject

class UsersPresenter <V : UsersMVPView, I : UsersMVPInteractor>
@Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable), UsersMVPPresenter<V, I> {

    override fun onViewPrepared() {
            setUsersToAdapter()
    }

    private fun setUsersToAdapter(){
        interactor.let {
            it?.getUserList()?.compose(schedulerProvider.ioToMainMaybeScheduler())?.subscribe { data ->
                    data.forEach { user ->
                        if (user != null) {
                            getView()?.addItem(UserItem(user))
                        }
                    }
                    getView()?.refreshUserRecycleView()
            }
        }
    }

    override fun openChatMessage() {
        getView()?.openChatFragment()
    }

    override fun setChatParterUser(user: User?) {
        interactor.let { inter ->
            inter?.updatePartnerUserInSharedPref(user)
        }
    }

}