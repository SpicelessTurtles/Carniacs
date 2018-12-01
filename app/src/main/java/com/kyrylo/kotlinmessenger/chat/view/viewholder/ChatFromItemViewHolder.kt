package com.kyrylo.kotlinmessenger.chat.view.viewholder

import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import com.kyrylo.kotlinmessenger.data.preferences.model.ViewHolderItem
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.chat_from_row.view.*

class ChatFromItemViewHolder(val text: String, val user: User) : Item<ViewHolder>(), ViewHolderItem {
    override fun getLayout(): Int {
        return R.layout.chat_from_row
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.textview_from_row.text = text

        //load user image into the star
        val uri = user.profileImageUri
        val targetImageView = viewHolder.itemView.imageView_chat_from_row

        Picasso.get().load(uri).fit().into(targetImageView)
    }
}
