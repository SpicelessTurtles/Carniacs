package com.kyrylo.kotlinmessenger.profile.interactor

import com.kyrylo.kotlinmessenger.base.interactor.BaseInteractor
import com.kyrylo.kotlinmessenger.data.preferences.PreferenceHelper
import com.kyrylo.kotlinmessenger.data.preferences.network.ApiHelper
import javax.inject.Inject

class ProfileInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper, apiHelper), ProfileMVPInteractor {

}