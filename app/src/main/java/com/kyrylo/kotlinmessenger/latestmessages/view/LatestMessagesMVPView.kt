package com.kyrylo.kotlinmessenger.latestmessages.view

import com.kyrylo.kotlinmessenger.base.view.MVPView
import com.kyrylo.kotlinmessenger.data.preferences.model.ChatMessage

interface LatestMessagesMVPView : MVPView {
    fun refreshRecyclerViewMessages(latestMessageRow : HashMap<String, ChatMessage>)
    fun openChatFragment()
}