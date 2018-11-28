package com.kyrylo.kotlinmessenger.register.presenter

import android.content.Context
import android.content.Intent
import com.kyrylo.kotlinmessenger.base.presenter.MVPPresenter
import com.kyrylo.kotlinmessenger.register.interactor.RegisterMVPInteractor
import com.kyrylo.kotlinmessenger.register.view.RegisterMVPView


interface RegisterMVPPresenter<V : RegisterMVPView, I : RegisterMVPInteractor> : MVPPresenter<V, I>{
    fun hideProgress()
    fun showProgress()
    fun onPerformRegister(context : Context, email : String, password : String)
    fun onOpenLoginActivity()
    fun onOpenImageChooserActivity()
    fun onOpenMainActivity()
    fun onImageChosen(requestCode: Int, resultCode: Int, data: Intent?)
}