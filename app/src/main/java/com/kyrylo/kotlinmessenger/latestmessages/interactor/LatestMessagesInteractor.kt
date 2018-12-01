package com.kyrylo.kotlinmessenger.latestmessages.interactor

import com.kyrylo.kotlinmessenger.base.interactor.BaseInteractor
import com.kyrylo.kotlinmessenger.data.preferences.PreferenceHelper
import com.kyrylo.kotlinmessenger.data.preferences.network.ApiHelper
import com.kyrylo.kotlinmessenger.data.preferences.model.ChatMessage
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import durdinapps.rxfirebase2.RxFirebaseChildEvent
import io.reactivex.Flowable
import io.reactivex.Maybe

import javax.inject.Inject


class LatestMessagesInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper, apiHelper), LatestMessagesMVPInteractor {

    override fun getLatestMessages(): Flowable<RxFirebaseChildEvent<ChatMessage>> = apiHelper.performLatestMessagesListener()
    override fun getCurrentUser(): Maybe<User> = apiHelper.performCatchLastUser()

    override fun updatePartnerUserInSharedPref(partnerUsername: User?) {
        preferenceHelper.let {
            it.setPartnerUserName(partnerUsername)
        }
    }
}