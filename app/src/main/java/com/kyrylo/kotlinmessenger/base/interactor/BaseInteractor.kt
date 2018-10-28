package com.kyrylo.kotlinmessenger.base.interactor

import com.google.firebase.auth.FirebaseAuth
import com.kyrylo.kotlinmessenger.data.preferences.PreferenceHelper
import com.kyrylo.kotlinmessenger.data.preferences.network.ApiHelper
import com.kyrylo.kotlinmessenger.utilities.AppConstants

open class BaseInteractor() : MVPInteractor {

    protected lateinit var preferenceHelper: PreferenceHelper
    protected lateinit var apiHelper: ApiHelper

    constructor(preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : this() {
        this.preferenceHelper = preferenceHelper
        this.apiHelper = apiHelper
    }

    override fun isUserLoggedIn() : Boolean {
        return FirebaseAuth.getInstance().uid != null
    }

    override fun performUserLogout() = preferenceHelper.let {
       // it.setCurrentUserId(null)
       // it.setAccessToken(null)
       // it.setCurrentUserEmail(null)
       // it.setCurrentUserLoggedInMode(AppConstants.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT)
    }

}