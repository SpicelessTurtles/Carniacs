package com.kyrylo.kotlinmessenger.register

import com.kyrylo.kotlinmessenger.register.interactor.RegisterInteractor
import com.kyrylo.kotlinmessenger.register.interactor.RegisterMVPInteractor
import com.kyrylo.kotlinmessenger.register.presenter.RegisterMVPPresenter
import com.kyrylo.kotlinmessenger.register.presenter.RegisterPresenter
import com.kyrylo.kotlinmessenger.register.view.RegisterMVPView
import dagger.Module
import dagger.Provides

@Module
class RegisterActivityModule{
    @Provides
    internal fun provideRegisterInteractor(registerInteractor: RegisterInteractor): RegisterMVPInteractor = registerInteractor

    @Provides
    internal fun provideRegisterPresenter(registerPresenter: RegisterPresenter<RegisterMVPView, RegisterMVPInteractor>)
            : RegisterMVPPresenter<RegisterMVPView, RegisterMVPInteractor> = registerPresenter
}