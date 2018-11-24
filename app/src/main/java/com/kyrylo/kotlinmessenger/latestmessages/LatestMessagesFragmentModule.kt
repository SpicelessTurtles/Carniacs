package com.kyrylo.kotlinmessenger.latestmessages

import com.kyrylo.kotlinmessenger.latestmessages.interactor.LatestMessagesInteractor
import com.kyrylo.kotlinmessenger.latestmessages.interactor.LatestMessagesMVPInteractor
import com.kyrylo.kotlinmessenger.latestmessages.presenter.LatestMessagesMVPPresenter
import com.kyrylo.kotlinmessenger.latestmessages.presenter.LatestMessagesPresenter
import com.kyrylo.kotlinmessenger.latestmessages.view.LatestMessagesMVPView
import com.kyrylo.kotlinmessenger.data.preferences.model.ChatMessage
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.Module
import dagger.Provides
import kotlin.collections.HashMap

@Module
class LatestMessagesFragmentModule {
    @Provides
    internal fun provideLastMessagesInteractor(lastMessagesInteractor: LatestMessagesInteractor): LatestMessagesMVPInteractor = lastMessagesInteractor

    @Provides
    internal fun provideLastMessagesPresenter(lastmessagesPresenter: LatestMessagesPresenter<LatestMessagesMVPView, LatestMessagesMVPInteractor>)
            : LatestMessagesMVPPresenter<LatestMessagesMVPView, LatestMessagesMVPInteractor> = lastmessagesPresenter

    @Provides
    internal fun provideLastMessages(): HashMap<String, ChatMessage> = HashMap()

    @Provides
    internal fun provideLastMessagesAdapter(): GroupAdapter<ViewHolder> = GroupAdapter()
}