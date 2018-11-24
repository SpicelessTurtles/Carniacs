package com.kyrylo.kotlinmessenger.latestmessages.presenter

import android.util.Log
import com.kyrylo.kotlinmessenger.base.presenter.BasePresenter
import com.kyrylo.kotlinmessenger.latestmessages.interactor.LatestMessagesMVPInteractor
import com.kyrylo.kotlinmessenger.latestmessages.view.LatestMessagesMVPView
import com.kyrylo.kotlinmessenger.data.preferences.model.ChatMessage
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import io.reactivex.disposables.CompositeDisposable
import com.kyrylo.kotlinmessenger.utilities.SchedulerProvider
import durdinapps.rxfirebase2.RxFirebaseChildEvent
import javax.inject.Inject

class LatestMessagesPresenter<V : LatestMessagesMVPView, I : LatestMessagesMVPInteractor> @Inject constructor(interactor: I, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = compositeDisposable), LatestMessagesMVPPresenter<V, I> {

    @Inject
    lateinit var latestMessagesMap: HashMap<String, ChatMessage>

    override fun onViewPrepared() {
        interactor?.let {
            it.getLatestMessages().compose(schedulerProvider.ioToMainFlowableScheduler())
                    .subscribe({ userData ->
                        when (userData.eventType) {
                            RxFirebaseChildEvent.EventType.ADDED -> {
                                setNewMessage(userData)
                            }
                            RxFirebaseChildEvent.EventType.CHANGED -> {
                                setNewMessage(userData)
                            }
                        }
                    }, { throwable ->
                        Log.d("error", throwable.message)
                    })
        }
    }

    private fun setNewMessage(userData : RxFirebaseChildEvent<ChatMessage>) {
        latestMessagesMap[userData.key] = userData.value
        getView()?.refreshRecyclerViewMessages(latestMessagesMap)
    }


    override fun openChatMessage() {
        getView()?.openChatFragment()
    }

    override fun setChatParterUser(user: User?) {
        interactor.let { inter ->
            inter?.updatePartnerUserInSharedPref(user)
        }
    }
}