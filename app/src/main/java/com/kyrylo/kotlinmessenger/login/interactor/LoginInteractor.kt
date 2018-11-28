package com.kyrylo.kotlinmessenger.login.interactor

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.kyrylo.kotlinmessenger.base.interactor.BaseInteractor
import com.kyrylo.kotlinmessenger.data.preferences.PreferenceHelper
import com.kyrylo.kotlinmessenger.data.preferences.network.ApiHelper
import javax.inject.Inject

class LoginInteractor @Inject constructor(private val mContext: Context, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(), LoginMVPInteractor {

    override fun getSignInInstance(email: String, password: String) : Task<AuthResult> =
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)

}