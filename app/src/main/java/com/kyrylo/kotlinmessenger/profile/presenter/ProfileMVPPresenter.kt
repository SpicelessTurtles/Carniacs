package com.kyrylo.kotlinmessenger.profile.presenter

import com.kyrylo.kotlinmessenger.base.presenter.MVPPresenter
import com.kyrylo.kotlinmessenger.profile.interactor.ProfileMVPInteractor
import com.kyrylo.kotlinmessenger.profile.view.ProfileMVPView

interface ProfileMVPPresenter<V : ProfileMVPView, I : ProfileMVPInteractor> : MVPPresenter<V, I> {

}