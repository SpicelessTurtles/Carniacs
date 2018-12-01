package com.kyrylo.kotlinmessenger.profile

import com.kyrylo.kotlinmessenger.profile.interactor.ProfileInteractor
import com.kyrylo.kotlinmessenger.profile.interactor.ProfileMVPInteractor
import com.kyrylo.kotlinmessenger.profile.presenter.ProfilePresenter
import com.kyrylo.kotlinmessenger.profile.presenter.ProfileMVPPresenter

import com.kyrylo.kotlinmessenger.profile.view.ProfileMVPView
import dagger.Module
import dagger.Provides

@Module
class ProfileFragmentModule {

    @Provides
    internal fun provideProfileInteractor(interactor: ProfileInteractor): ProfileMVPInteractor = interactor

    @Provides
    internal fun provideProfilePresenter(presenter: ProfilePresenter<ProfileMVPView, ProfileMVPInteractor>)
            : ProfileMVPPresenter<ProfileMVPView, ProfileMVPInteractor> = presenter

}