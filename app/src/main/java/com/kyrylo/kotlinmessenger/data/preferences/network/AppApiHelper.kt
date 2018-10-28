package com.kyrylo.kotlinmessenger.data.preferences.network

import com.kyrylo.kotlinmessenger.data.preferences.responces.GoogleNewsResponse
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jyotidubey on 04/01/18.
 */
class AppApiHelper @Inject constructor(private val apiHeader: ApiHeader) : ApiHelper {

    override fun performNewsApi(): Observable<GoogleNewsResponse> =
            Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GOOGLE_NEWS)
                    .build()
                    .getObjectObservable(GoogleNewsResponse::class.java)
}