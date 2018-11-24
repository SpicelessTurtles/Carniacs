package com.kyrylo.kotlinmessenger.main.interactor


import com.kyrylo.kotlinmessenger.base.interactor.BaseInteractor
import com.kyrylo.kotlinmessenger.data.preferences.network.ApiHelper
import com.kyrylo.kotlinmessenger.data.preferences.PreferenceHelper
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import io.reactivex.Maybe
import javax.inject.Inject

/**
 * Created by jyotidubey on 08/01/18.
 */
class MainInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper = preferenceHelper, apiHelper = apiHelper), MainMVPInteractor {

    override fun updateCurrentUserInSharedPref(currentUsername: User?){
        preferenceHelper.let {
            it.setCurrentUser(currentUsername)
        }
    }

    override fun getCurrentUser(): Maybe<User> = apiHelper.performCatchLastUser()
}


