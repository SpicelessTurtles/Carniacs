package com.kyrylo.kotlinmessenger.register.interactor

import android.content.Context
import com.kyrylo.kotlinmessenger.base.interactor.BaseInteractor
import com.kyrylo.kotlinmessenger.data.preferences.PreferenceHelper
import com.kyrylo.kotlinmessenger.data.preferences.network.ApiHelper
import javax.inject.Inject

class RegisterInteractor
@Inject constructor(private val mContext: Context, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(), RegisterMVPInteractor