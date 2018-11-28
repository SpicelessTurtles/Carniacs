package com.kyrylo.kotlinmessenger.register.view

import android.content.Intent
import android.net.Uri
import com.kyrylo.kotlinmessenger.base.view.MVPView

interface RegisterMVPView : MVPView{
    fun openLoginActivity()
    fun openImageChooserActivity()
    fun imageChosen(requestCode: Int, resultCode: Int, data: Intent?)
    fun getSelectedPhoto() : Uri?
    fun getRegisterUserName(): String
    fun openMainActivity()
}