package com.kyrylo.kotlinmessenger.base.interactor

interface MVPInteractor {
    fun isUserLoggedIn(): Boolean

    fun performUserLogout()
}