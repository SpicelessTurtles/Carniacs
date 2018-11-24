package com.kyrylo.kotlinmessenger.chat.presenter

import com.kyrylo.kotlinmessenger.base.presenter.MVPPresenter
import com.kyrylo.kotlinmessenger.chat.interactor.ChatLogMVPInteractor
import com.kyrylo.kotlinmessenger.chat.view.ChatLogMVPView

interface ChatLogMVPPresenter <V : ChatLogMVPView, I : ChatLogMVPInteractor> : MVPPresenter<V, I> {
    fun onViewPrepared()
    fun performSendMessages()
    fun listenForMessages()
}