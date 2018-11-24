package com.kyrylo.kotlinmessenger.chat.view

import android.widget.EditText
import com.kyrylo.kotlinmessenger.base.view.MVPView
import com.xwray.groupie.Group

interface ChatLogMVPView : MVPView{

    fun scrollToPosition()
    fun addItem(item: Group)
    fun returnEditText() : EditText
}