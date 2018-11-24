package com.kyrylo.kotlinmessenger.chat.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class ChatLogActivity : AppCompatActivity() {

    companion object {
        val TAG = "ChatLog"
    }

    val adapter = GroupAdapter<ViewHolder>()

    var toUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_chat_log)
/*
        recycler_view_chatlog.adapter = adapter

        toUser = intent.getParcelableExtra(NewMessageActivity.USER_KEY)
        supportActionBar?.title = toUser?.username

        listenForMessages()

        send_button_chatlog.setOnClickListener {
            Log.d(TAG, "Attempt")
            performSendMessage()
        }*/
    }

    private fun listenForMessages() {

        /*    val fromId = FirebaseAuth.getInstance().currentUser?.uid
        val toId = toUser?.uid
        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId")

        ref.addChildEventListener(object : ChildEventListener {

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java)
                if (chatMessage != null) {
                    if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                      //    val currentUser = LatestMessagesActivity.currentUser?: return
                        //  adapter.add(ChatFromItemViewHolder(chatMessage.text,currentUser))
                    } else {
                      //  adapter.add(ChatToItem(chatMessage.text,toUser!!))
                    }
                }

                recycler_view_chatlog.scrollToPosition(adapter.itemCount - 1)

            }

            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildRemoved(p0: DataSnapshot) {
            }

        })*/
    }

    private fun performSendMessage() {
/*
        val text = edittext_chatlog.text.toString()

        val user = intent.getParcelableExtra<User>(USER_KEY)

        val fromId = FirebaseAuth.getInstance().uid
        val toId = user.uid

        if (fromId == null) return

//        val reference = FirebaseDatabase.getInstance().getReference("/messages").push()
        val reference = FirebaseDatabase.getInstance().getReference("user-messages/$fromId/$toId").push()

        val toReference = FirebaseDatabase.getInstance().getReference("user-messages/$toId/$fromId").push()

        val chatMessage = ChatMessage(reference.key!!, text, fromId, toId, System.currentTimeMillis() / 1000)

        reference.setValue(chatMessage)
                .addOnSuccessListener {
                    edittext_chatlog.text.clear()
                    recycler_view_chatlog.scrollToPosition(adapter.itemCount - 1)
                    Log.d(TAG, "Saved our chat message...${reference.key}")
                }

        toReference.setValue(chatMessage)

        val latestMessageRef = FirebaseDatabase.getInstance()
                .getReference("/latest-messages/$fromId/$toId")

        latestMessageRef.setValue(chatMessage)

        val latestMessageToRef = FirebaseDatabase.getInstance()
                .getReference("/latest-messages/$toId/$fromId")

        latestMessageToRef.setValue(chatMessage)
*/
    }

    }

