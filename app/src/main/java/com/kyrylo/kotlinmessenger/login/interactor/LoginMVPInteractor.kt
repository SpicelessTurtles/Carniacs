package com.kyrylo.kotlinmessenger.login.interactor

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.StorageReference
import com.kyrylo.kotlinmessenger.base.interactor.MVPInteractor

interface LoginMVPInteractor : MVPInteractor {
    fun getSignInInstance(email: String, password: String) : Task<AuthResult>
}