package com.kyrylo.kotlinmessenger.chat

import com.kyrylo.kotlinmessenger.chat.interactor.ChatLogInteractor
import com.kyrylo.kotlinmessenger.chat.interactor.ChatLogMVPInteractor
import com.kyrylo.kotlinmessenger.chat.presenter.ChatLogMVPPresenter
import com.kyrylo.kotlinmessenger.chat.presenter.ChatLogPresenter
import com.kyrylo.kotlinmessenger.chat.view.ChatLogMVPView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.Module
import dagger.Provides

@Module
class ChatLogFragmentModule {

    @Provides
    internal fun provideChatLogInteractor(chatLogInterator: ChatLogInteractor): ChatLogMVPInteractor = chatLogInterator

    @Provides
    internal fun provideChatLogPresenter(chatLogPresenter: ChatLogPresenter<ChatLogMVPView, ChatLogMVPInteractor>)
            : ChatLogMVPPresenter<ChatLogMVPView, ChatLogMVPInteractor> = chatLogPresenter

    @Provides
    internal fun provideChatAdapter(): GroupAdapter<ViewHolder> = GroupAdapter()
}