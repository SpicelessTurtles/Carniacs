package com.kyrylo.kotlinmessenger.chat.interactor

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.kyrylo.kotlinmessenger.base.interactor.BaseInteractor
import com.kyrylo.kotlinmessenger.chat.view.viewholder.ChatFromItemViewHolder
import com.kyrylo.kotlinmessenger.chat.view.viewholder.ChatToItemViewHolder
import com.kyrylo.kotlinmessenger.data.preferences.PreferenceHelper
import com.kyrylo.kotlinmessenger.data.preferences.model.ChatMessage
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import com.kyrylo.kotlinmessenger.data.preferences.network.ApiHelper
import durdinapps.rxfirebase2.RxFirebaseChildEvent
import io.reactivex.Flowable

import javax.inject.Inject

class ChatLogInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper, apiHelper), ChatLogMVPInteractor {

    lateinit var onSendMessageSuccess : OnSuccessListener<Void>

    override fun getCurrentUser() : User? {
        return preferenceHelper.getCurrentUser()
    }

    override fun getPartnerUser() : User?{
        return preferenceHelper.getPartnerUser()
    }

    override fun getChatReference(): Flowable<RxFirebaseChildEvent<ChatMessage>> {
        return apiHelper.listenReceivedMessages()
    }

    override fun performSendMessage(text : String) {

        val toUser = getPartnerUser()

        val fromId = getCurrentUser()?.uid

        val toId = toUser?.uid

        if (fromId == null) return

        val reference = FirebaseDatabase.getInstance().getReference("user-messages/$fromId/$toId").push()

        val toReference = FirebaseDatabase.getInstance().getReference("user-messages/$toId/$fromId").push()

        val chatMessage = ChatMessage(reference.key!!, text, fromId, toId!!, System.currentTimeMillis() / 1000)

        reference.setValue(chatMessage)
                .addOnSuccessListener(onSendMessageSuccess)
        toReference.setValue(chatMessage)

        val latestMessageRef = FirebaseDatabase.getInstance()
                .getReference("/latest-messages/$fromId/$toId")

        latestMessageRef.setValue(chatMessage)

        val latestMessageToRef = FirebaseDatabase.getInstance()
                .getReference("/latest-messages/$toId/$fromId")

        latestMessageToRef.setValue(chatMessage)

    }

    override fun setOnSuccessActionListener(listener: OnSuccessListener<Void>) {
        onSendMessageSuccess = listener
    }

}