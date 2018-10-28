package com.kyrylo.kotlinmessenger.main.interactor


import com.kyrylo.kotlinmessenger.base.interactor.BaseInteractor
import com.kyrylo.kotlinmessenger.data.preferences.network.ApiHelper
import com.kyrylo.kotlinmessenger.data.preferences.PreferenceHelper
import javax.inject.Inject

/**
 * Created by jyotidubey on 08/01/18.
 */
class MainInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper = preferenceHelper, apiHelper = apiHelper), MainMVPInteractor {

}


