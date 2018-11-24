package com.kyrylo.kotlinmessenger.users.interactor

import com.kyrylo.kotlinmessenger.base.interactor.MVPInteractor
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import io.reactivex.Maybe

interface UsersMVPInteractor : MVPInteractor {
    fun getUserList(): Maybe<List<User?>>
    fun updatePartnerUserInSharedPref(user : User?)
}