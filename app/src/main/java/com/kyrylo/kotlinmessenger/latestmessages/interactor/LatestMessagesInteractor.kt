package com.kyrylo.kotlinmessenger.latestmessages.interactor

import javax.inject.Inject


class LatestMessagesInteractor @Inject constructor(): LatestMessagesMVPInteractor{
    override fun isUserLoggedIn(): Boolean {
        return false
    }

    override fun performUserLogout() {
    }

}