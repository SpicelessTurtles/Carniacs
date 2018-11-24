package com.kyrylo.kotlinmessenger.latestmessages.interactor

import com.kyrylo.kotlinmessenger.base.interactor.MVPInteractor
import com.kyrylo.kotlinmessenger.data.preferences.model.ChatMessage
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import durdinapps.rxfirebase2.RxFirebaseChildEvent
import io.reactivex.Flowable
import io.reactivex.Maybe

interface LatestMessagesMVPInteractor : MVPInteractor {
    fun getLatestMessages(): Flowable<RxFirebaseChildEvent<ChatMessage>>
    fun getCurrentUser(): Maybe<User>
    fun updateCurrentUserInSharedPref(currentUsername: User?)
    fun updatePartnerUserInSharedPref(partnerUsername: User?)
}