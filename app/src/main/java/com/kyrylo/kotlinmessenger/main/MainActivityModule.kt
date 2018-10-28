package com.mindorks.framework.mvp.ui.main

import com.kyrylo.kotlinmessenger.main.interactor.MainInteractor
import com.kyrylo.kotlinmessenger.main.interactor.MainMVPInteractor
import com.kyrylo.kotlinmessenger.main.presenter.MainMVPPresenter
import com.kyrylo.kotlinmessenger.main.presenter.MainPresenter
import com.kyrylo.kotlinmessenger.main.view.MainMVPView
import dagger.Module
import dagger.Provides

/**
 * Created by jyotidubey on 09/01/18.
 */
@Module
class MainActivityModule {

    @Provides
    internal fun provideMainInteractor(mainInteractor: MainInteractor): MainMVPInteractor = mainInteractor

    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenter<MainMVPView, MainMVPInteractor>)
            : MainMVPPresenter<MainMVPView, MainMVPInteractor> = mainPresenter

}