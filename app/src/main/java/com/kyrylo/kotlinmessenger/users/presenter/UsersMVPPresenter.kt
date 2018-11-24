package com.kyrylo.kotlinmessenger.users.presenter

import com.kyrylo.kotlinmessenger.base.presenter.MVPPresenter
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import com.kyrylo.kotlinmessenger.users.interactor.UsersMVPInteractor
import com.kyrylo.kotlinmessenger.users.view.UsersMVPView

interface UsersMVPPresenter <V : UsersMVPView, I : UsersMVPInteractor> : MVPPresenter<V, I> {
    fun onViewPrepared()
    fun openChatMessage()
    fun setChatParterUser(user: User?)
}