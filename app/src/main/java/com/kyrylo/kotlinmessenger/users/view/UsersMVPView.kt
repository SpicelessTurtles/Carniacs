package com.kyrylo.kotlinmessenger.users.view

import com.kyrylo.kotlinmessenger.base.view.MVPView
import com.kyrylo.kotlinmessenger.users.view.viewholder.UserItem

interface UsersMVPView : MVPView {

    fun addItem(user : UserItem)
    fun refreshUserRecycleView()
    fun openChatFragment()

}