package com.kyrylo.kotlinmessenger.latestmessages

import com.kyrylo.kotlinmessenger.latestmessages.interactor.LatestMessagesInteractor
import com.kyrylo.kotlinmessenger.latestmessages.interactor.LatestMessagesMVPInteractor
import com.kyrylo.kotlinmessenger.latestmessages.presenter.LatestMessagesMVPPresenter
import com.kyrylo.kotlinmessenger.latestmessages.presenter.LatestMessagesPresenter
import com.kyrylo.kotlinmessenger.latestmessages.view.LatestMessagesMVPView
import dagger.Module
import dagger.Provides

@Module
class LatestMessagesModule {
    @Provides
    internal fun provideLastMessagesInteractor(lastMessagesInteractor: LatestMessagesInteractor): LatestMessagesMVPInteractor = lastMessagesInteractor

    @Provides
    internal fun provideLastMessagesPresenter(lastmessagesPresenter: LatestMessagesPresenter<LatestMessagesMVPView, LatestMessagesMVPInteractor>)
            : LatestMessagesMVPPresenter<LatestMessagesMVPView, LatestMessagesMVPInteractor> = lastmessagesPresenter
}