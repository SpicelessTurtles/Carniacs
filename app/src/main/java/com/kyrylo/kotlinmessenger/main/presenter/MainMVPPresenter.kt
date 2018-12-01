package com.kyrylo.kotlinmessenger.main.presenter

import com.kyrylo.kotlinmessenger.base.presenter.MVPPresenter
import com.kyrylo.kotlinmessenger.main.interactor.MainMVPInteractor
import com.kyrylo.kotlinmessenger.main.view.MainMVPView

/**
 * Created by jyotidubey on 08/01/18.
 */
interface MainMVPPresenter<V : MainMVPView, I : MainMVPInteractor> : MVPPresenter<V, I> {

    fun onDrawerOptionNewsClick() : Unit?
    fun onDrawerOptionProfileClick() : Unit?
    fun onLatestMessagesClick(): Unit?
    fun onDrawerOptionLogoutClick()
    fun openChatFragment()
    fun onNewMessageClick()
    fun onArticleActivityClick()

}