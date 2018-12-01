package com.kyrylo.kotlinmessenger.main.view

import com.kyrylo.kotlinmessenger.base.view.MVPView
import com.kyrylo.kotlinmessenger.data.preferences.model.GoogleNews

/**
 * Created by jyotidubey on 08/01/18.
 */
interface MainMVPView : MVPView {
    fun openLoginActivity()
    fun openLatestMessagesFragment()
    fun openNewsFragment()
    fun openChatFragment()
    fun openUsersFragment()
    fun openProfileFragment()
    fun lockDrawer(): Unit?
    fun unlockDrawer(): Unit?
    fun openArticleActivity()
}