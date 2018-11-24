package com.kyrylo.kotlinmessenger.splash.view

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.base.view.BaseActivity
import com.kyrylo.kotlinmessenger.login.view.LoginActivity
import com.kyrylo.kotlinmessenger.main.view.MainActivity
import com.kyrylo.kotlinmessenger.splash.interactor.SplashMVPInteractor
import com.kyrylo.kotlinmessenger.splash.presenter.SplashMVPPresenter
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashMVPActivity : BaseActivity(), SplashMVPView {
    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {
    }

    @Inject
    lateinit var presenter: SplashMVPPresenter<SplashMVPView, SplashMVPInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }


    override fun showSuccessToast() {
    }

    override fun showErrorToast() {
    }

    override fun logoAnimation(){
        val backgroundAnim : Animation = AnimationUtils.loadAnimation(this@SplashMVPActivity,R.anim.splash_transition)
        val logoAnim : Animation = AnimationUtils.loadAnimation(this@SplashMVPActivity,R.anim.slight_right_logo)
        splashscreen_background.startAnimation(backgroundAnim)
        splashscreen_logo.startAnimation(logoAnim)
    }

    override fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
