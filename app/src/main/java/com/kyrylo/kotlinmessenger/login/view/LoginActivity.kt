package com.kyrylo.kotlinmessenger.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.base.view.BaseActivity
import com.kyrylo.kotlinmessenger.login.interactor.LoginMVPInteractor
import com.kyrylo.kotlinmessenger.login.presenter.LoginMVPPresenter
import com.kyrylo.kotlinmessenger.main.view.MainActivity
import com.kyrylo.kotlinmessenger.register.view.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginMVPView {

    @Inject
    internal lateinit var presenter: LoginMVPPresenter<LoginMVPView, LoginMVPInteractor>

    override fun performMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun performRegisterActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun hideProgress() {
        progressBar_login.visibility = GONE
    }

    override fun showProgress() {
        progressBar_login.visibility = VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        presenter.onAttach(this)
        hideProgress()

        login_button_login.setOnClickListener {
            val email = email_edittext_login.text.toString()
            val password = password_edittext_login.text.toString()
            presenter.onSignIn(this@LoginActivity, email, password)
        }

        back_to_register_textview.setOnClickListener {
          presenter.onPerformRegisterActivity()
        }
    }
}
