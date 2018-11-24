package com.kyrylo.kotlinmessenger.chat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.base.view.BaseFragment
import com.kyrylo.kotlinmessenger.chat.interactor.ChatLogMVPInteractor
import com.kyrylo.kotlinmessenger.chat.presenter.ChatLogMVPPresenter
import com.xwray.groupie.Group

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_chat_log.*
import javax.inject.Inject

class ChatLogFragment : BaseFragment(), ChatLogMVPView {

    @Inject
    lateinit var presenter: ChatLogMVPPresenter<ChatLogMVPView, ChatLogMVPInteractor>

    @Inject
    lateinit var adapter: GroupAdapter<ViewHolder>

    companion object {
        val TAG = "ChatLogFragment"
        fun newInstance(): ChatLogFragment {
            return ChatLogFragment()
        }
    }

    override fun addItem(item: Group) {
        adapter.add(item)
    }

    override fun setUp() {
        recycler_view_chatlog.adapter = adapter

        presenter.listenForMessages()

        send_button_chatlog.setOnClickListener {
            presenter.performSendMessages()
        }

        presenter.onViewPrepared()
    }

    override fun scrollToPosition() {
        recycler_view_chatlog.scrollToPosition(adapter.itemCount - 1)
    }

    override fun returnEditText() : EditText {
       return edittext_chatlog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
      inflater.inflate(R.layout.fragment_chat_log, container, false)



}