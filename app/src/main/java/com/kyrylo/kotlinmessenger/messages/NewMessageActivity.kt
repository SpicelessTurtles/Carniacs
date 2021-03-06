package com.kyrylo.kotlinmessenger.messages

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.chat.view.ChatLogActivity
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import com.kyrylo.kotlinmessenger.users.view.viewholder.UserItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class NewMessageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_new_message)

        supportActionBar?.title = "Select User"

        fetchUsers()
    }


    companion object {
        val USER_KEY = "FROM_USER_KEY"
    }

    private fun fetchUsers() {
        val ref = FirebaseDatabase.getInstance().getReference("/users")
        val adapter = GroupAdapter<ViewHolder>()

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    Log.d("NewMessage", it.toString())
                    val user = it.getValue(User::class.java)
                    if (user != null) {
                        adapter.add(UserItem(user))
                    }
                }

                adapter.setOnItemClickListener { item, view ->

                    val userItem = item as UserItem
                    val intent = Intent(view.context, ChatLogActivity::class.java)
                    intent.putExtra(USER_KEY, userItem.user)
                    startActivity(intent)

                    finish()
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })
    }

//    class UserItem(val user: User) : Item<ViewHolder>() {
//        override fun getLayout(): Int {
//            return R.layout.user_row_new_message
//        }
//
//        override fun bind(viewHolder: ViewHolder, position: Int) {
//            viewHolder.itemView.username_textview_new_message.text = user.username
//            Picasso.get().load(user.profileImageUri).into(viewHolder.itemView.imageview_new_message)
//        }
//
//    }
}
