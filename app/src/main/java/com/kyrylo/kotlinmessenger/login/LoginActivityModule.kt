package com.kyrylo.kotlinmessenger.login

import com.kyrylo.kotlinmessenger.login.interactor.LoginInteractor
import com.kyrylo.kotlinmessenger.login.interactor.LoginMVPInteractor
import com.kyrylo.kotlinmessenger.login.presenter.LoginMVPPresenter
import com.kyrylo.kotlinmessenger.login.presenter.LoginPresenter
import com.kyrylo.kotlinmessenger.login.view.LoginMVPView
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule{
    @Provides
    internal fun provideLoginInteractor(loginInteractor: LoginInteractor): LoginMVPInteractor = loginInteractor

    @Provides
    internal fun provideLoginPresenter(loginPresenter: LoginPresenter<LoginMVPView, LoginMVPInteractor>)
            : LoginMVPPresenter<LoginMVPView, LoginMVPInteractor> = loginPresenter
}