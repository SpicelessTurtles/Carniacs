package com.kyrylo.kotlinmessenger.data.preferences.network

import com.kyrylo.kotlinmessenger.data.preferences.responces.GoogleNewsResponse
import io.reactivex.Observable

/**
 * Created by jyotidubey on 04/01/18.
 */
interface ApiHelper {

  fun performNewsApi() : Observable<GoogleNewsResponse>

}