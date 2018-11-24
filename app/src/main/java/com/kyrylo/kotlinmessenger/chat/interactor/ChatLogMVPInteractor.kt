package com.kyrylo.kotlinmessenger.chat.interactor

import com.google.android.gms.tasks.OnSuccessListener
import com.kyrylo.kotlinmessenger.base.interactor.MVPInteractor
import com.kyrylo.kotlinmessenger.data.preferences.model.ChatMessage
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import durdinapps.rxfirebase2.RxFirebaseChildEvent
import io.reactivex.Flowable

interface ChatLogMVPInteractor : MVPInteractor {
    fun getCurrentUser(): User?
    fun getPartnerUser(): User?
    fun getChatReference(): Flowable<RxFirebaseChildEvent<ChatMessage>>
    fun performSendMessage(text: String)
    fun setOnSuccessActionListener(listener: OnSuccessListener<Void>)
}