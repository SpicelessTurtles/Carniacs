package com.kyrylo.kotlinmessenger.news.interactor

import com.kyrylo.kotlinmessenger.base.interactor.BaseInteractor
import com.kyrylo.kotlinmessenger.data.preferences.responces.GoogleNewsResponse
import io.reactivex.Observable
import javax.inject.Inject
import com.kyrylo.kotlinmessenger.data.preferences.*
import com.kyrylo.kotlinmessenger.data.preferences.network.ApiHelper

class NewsInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper, apiHelper), NewsMVPInteractor {

    override fun getNewsList(): Observable<GoogleNewsResponse>  = apiHelper.performNewsApi()

}