package com.kyrylo.kotlinmessenger.news.interactor

import com.kyrylo.kotlinmessenger.base.interactor.MVPInteractor
import com.kyrylo.kotlinmessenger.data.preferences.responces.GoogleNewsResponse
import io.reactivex.Observable

interface NewsMVPInteractor : MVPInteractor {

    fun getNewsList(): Observable<GoogleNewsResponse>

}