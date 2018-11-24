package com.kyrylo.kotlinmessenger.users.interactor

import com.kyrylo.kotlinmessenger.base.interactor.BaseInteractor
import com.kyrylo.kotlinmessenger.data.preferences.PreferenceHelper
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import com.kyrylo.kotlinmessenger.data.preferences.network.ApiHelper
import io.reactivex.Maybe
import javax.inject.Inject

class UsersInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper, apiHelper), UsersMVPInteractor {
    override fun getUserList(): Maybe<List<User?>> = apiHelper.performReadUserList()
    override fun updatePartnerUserInSharedPref(user: User?) {
        preferenceHelper.let {
            it.setPartnerUserName(user)
        }
    }
}