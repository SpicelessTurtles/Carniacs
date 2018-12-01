package com.kyrylo.kotlinmessenger.base.interactor

import com.google.firebase.auth.FirebaseAuth
import com.kyrylo.kotlinmessenger.data.preferences.PreferenceHelper
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import com.kyrylo.kotlinmessenger.data.preferences.network.ApiHelper
import com.kyrylo.kotlinmessenger.utilities.AppConstants

open class BaseInteractor() : MVPInteractor {

    protected lateinit var preferenceHelper: PreferenceHelper
    protected lateinit var apiHelper: ApiHelper

    constructor(preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : this() {
        this.preferenceHelper = preferenceHelper
        this.apiHelper = apiHelper
    }

    override fun isUserLoggedIn(): Boolean {
        return FirebaseAuth.getInstance().currentUser != null
    }

    override fun performUserLogout() = preferenceHelper.let {

        FirebaseAuth.getInstance().signOut()
        preferenceHelper.cleanSharedPreference()

    }

}