package com.kyrylo.kotlinmessenger.splash

import com.kyrylo.kotlinmessenger.splash.interactor.SplashInteractor
import com.kyrylo.kotlinmessenger.splash.interactor.SplashMVPInteractor
import com.kyrylo.kotlinmessenger.splash.presenter.SplashMVPPresenter
import com.kyrylo.kotlinmessenger.splash.presenter.SplashPresenter
import com.kyrylo.kotlinmessenger.splash.view.SplashMVPView
import dagger.Module
import dagger.Provides

/**
 * Created by jyotidubey on 06/01/18.
 */
@Module
class SplashActivityModule {

    @Provides
    internal fun provideSplashInteractor(splashInteractor: SplashInteractor): SplashMVPInteractor = splashInteractor

    @Provides
    internal fun provideSplashPresenter(splashPresenter: SplashPresenter<SplashMVPView, SplashMVPInteractor>)
            : SplashMVPPresenter<SplashMVPView, SplashMVPInteractor> = splashPresenter
}