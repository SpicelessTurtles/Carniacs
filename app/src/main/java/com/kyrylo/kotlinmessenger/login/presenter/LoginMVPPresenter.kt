package com.kyrylo.kotlinmessenger.login.presenter

import android.content.Context
import com.kyrylo.kotlinmessenger.base.presenter.MVPPresenter
import com.kyrylo.kotlinmessenger.login.interactor.LoginMVPInteractor
import com.kyrylo.kotlinmessenger.login.view.LoginMVPView
import com.kyrylo.kotlinmessenger.register.interactor.RegisterMVPInteractor
import com.kyrylo.kotlinmessenger.register.view.RegisterMVPView

interface LoginMVPPresenter <V : LoginMVPView, I : LoginMVPInteractor> : MVPPresenter<V, I> {
    fun onPerformMainActivity()
    fun onPerformRegisterActivity()
    fun onSignIn(context : Context, email: String, password: String)
}