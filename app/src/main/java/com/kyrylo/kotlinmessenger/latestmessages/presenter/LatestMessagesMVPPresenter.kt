package com.kyrylo.kotlinmessenger.latestmessages.presenter

import com.kyrylo.kotlinmessenger.base.presenter.MVPPresenter
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import com.kyrylo.kotlinmessenger.latestmessages.interactor.LatestMessagesMVPInteractor
import com.kyrylo.kotlinmessenger.latestmessages.view.LatestMessagesMVPView

interface LatestMessagesMVPPresenter<V : LatestMessagesMVPView, I : LatestMessagesMVPInteractor> : MVPPresenter<V, I>{
    fun onViewPrepared()
    fun openChatMessage()
    fun setChatParterUser(user: User?)
}