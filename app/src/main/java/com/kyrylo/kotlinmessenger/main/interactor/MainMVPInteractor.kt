package com.kyrylo.kotlinmessenger.main.interactor

import com.kyrylo.kotlinmessenger.base.interactor.MVPInteractor
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import io.reactivex.Maybe

/**
 * Created by jyotidubey on 08/01/18.
 */
interface MainMVPInteractor : MVPInteractor {
   fun updateCurrentUserInSharedPref(currentUsername: User?)
   fun getCurrentUser(): Maybe<User>
}