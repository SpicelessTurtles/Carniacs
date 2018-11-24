package com.kyrylo.kotlinmessenger.chat.presenter

import android.util.Log
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.kyrylo.kotlinmessenger.base.presenter.BasePresenter
import com.kyrylo.kotlinmessenger.chat.interactor.ChatLogMVPInteractor
import com.kyrylo.kotlinmessenger.chat.view.ChatLogMVPView
import com.kyrylo.kotlinmessenger.chat.view.viewholder.ChatFromItemViewHolder
import com.kyrylo.kotlinmessenger.chat.view.viewholder.ChatToItemViewHolder
import com.kyrylo.kotlinmessenger.data.preferences.model.ChatMessage
import com.kyrylo.kotlinmessenger.utilities.SchedulerProvider
import durdinapps.rxfirebase2.RxFirebaseChildEvent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ChatLogPresenter<V : ChatLogMVPView, I : ChatLogMVPInteractor>
@Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable), ChatLogMVPPresenter<V, I>, OnSuccessListener<Void> {


    /**
     *
     */
    override fun onSuccess(p0: Void?) {
        getView()?.returnEditText()?.text?.clear()
        getView()?.scrollToPosition()
    }

    override fun performSendMessages() {
        val inputText = getView()?.returnEditText()?.text.toString()
        interactor?.performSendMessage(inputText)
    }

    override fun listenForMessages() {
        interactor?.let {
            val fromUser = it.getCurrentUser()
            val toUser = it.getPartnerUser()
            val fromId = it.getCurrentUser()?.uid

            it.getChatReference().compose(schedulerProvider.ioToMainFlowableScheduler())
                    .subscribe({ chatMessage ->
                        when (chatMessage.eventType) {
                            RxFirebaseChildEvent.EventType.ADDED -> {
                                if (chatMessage != null) {
                                    if (chatMessage.value.fromId == fromId) {
                                        getView()?.addItem(ChatFromItemViewHolder(chatMessage.value.text, fromUser!!))
                                    } else {
                                        getView()?.addItem(ChatToItemViewHolder(chatMessage.value.text, toUser!!))
                                    }
                                }
                            }
                        }
                    }, { throwable ->
                        Log.d("error", throwable.message)
                    })
        }
    }

    override fun onViewPrepared() {
        interactor?.setOnSuccessActionListener(this)
    }

}