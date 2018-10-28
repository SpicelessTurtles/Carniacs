package com.kyrylo.kotlinmessenger.splash.view

import com.kyrylo.kotlinmessenger.base.view.MVPView

/**
 * Created by jyotidubey on 04/01/18.
 */
interface SplashMVPView : MVPView {

    fun showSuccessToast()
    fun showErrorToast()
    fun openMainActivity()
    fun openLoginActivity()
    fun logoAnimation()
}