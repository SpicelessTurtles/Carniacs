package com.kyrylo.kotlinmessenger.data.preferences.network

import com.kyrylo.kotlinmessenger.data.preferences.responces.GoogleNewsResponse
import com.kyrylo.kotlinmessenger.data.preferences.model.ChatMessage
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable
import javax.inject.Inject
import durdinapps.rxfirebase2.RxFirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kyrylo.kotlinmessenger.data.preferences.PreferenceHelper
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseChildEvent
import io.reactivex.Flowable
import io.reactivex.Maybe
import javax.inject.Named


/**
 * Created by jyotidubey on 04/01/18.
 */
class AppApiHelper @Inject constructor(var preferenceHelper: PreferenceHelper,
                                       @Named("LatestMessages") val latestMessagesQuery: DatabaseReference,
                                       @Named("CurrentUser") val currentUserQuery: DatabaseReference,
                                       @Named("UserList") val userListQuery: DatabaseReference) : ApiHelper {


    override fun performCatchLastUser(): Maybe<User> =
            RxFirebaseDatabase.observeSingleValueEvent<User>(currentUserQuery, User::class.java)


    override fun performLatestMessagesListener(): Flowable<RxFirebaseChildEvent<ChatMessage>> = RxFirebaseDatabase.observeChildEvent<ChatMessage>(latestMessagesQuery, ChatMessage::class.java)

    override fun performReadUserList(): Maybe<List<User?>> =
            RxFirebaseDatabase.observeSingleValueEvent(userListQuery, DataSnapshotMapper.listOf(User::class.java))

    override fun performNewsApi(): Observable<GoogleNewsResponse> =
            Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GOOGLE_NEWS)
                    .build()
                    .getObjectObservable(GoogleNewsResponse::class.java)

    override fun listenReceivedMessages(): Flowable<RxFirebaseChildEvent<ChatMessage>> =
        RxFirebaseDatabase.observeChildEvent<ChatMessage>(FirebaseDatabase.getInstance().getReference("/user-messages/${preferenceHelper.getCurrentUser()?.uid}/${preferenceHelper.getPartnerUser()?.uid}"), ChatMessage::class.java)
}