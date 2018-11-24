package com.kyrylo.kotlinmessenger.data.preferences.network

import com.kyrylo.kotlinmessenger.data.preferences.responces.GoogleNewsResponse
import com.kyrylo.kotlinmessenger.data.preferences.model.ChatMessage
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import durdinapps.rxfirebase2.RxFirebaseChildEvent
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable

/**
 * Created by jyotidubey on 04/01/18.
 */
interface ApiHelper {

  fun performNewsApi() : Observable<GoogleNewsResponse>
  fun performLatestMessagesListener() : Flowable<RxFirebaseChildEvent<ChatMessage>>
  fun listenReceivedMessages(): Flowable<RxFirebaseChildEvent<ChatMessage>>
  fun performCatchLastUser(): Maybe<User>
  fun performReadUserList(): Maybe<List<User?>>

}