package com.kyrylo.kotlinmessenger.users.view.viewholder

import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.user_row_new_message.view.*

class UserItem(val user: User) : Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.user_row_new_message
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.username_textview_new_message.text = user.username
        Picasso.get().load(user.profileImageUri).fit().into(viewHolder.itemView.imageview_new_message)
    }

}