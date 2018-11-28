package com.kyrylo.kotlinmessenger.login.presenter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.kyrylo.kotlinmessenger.base.presenter.BasePresenter
import com.kyrylo.kotlinmessenger.login.interactor.LoginMVPInteractor
import com.kyrylo.kotlinmessenger.login.view.LoginMVPView
import com.kyrylo.kotlinmessenger.main.view.MainActivity
import com.kyrylo.kotlinmessenger.utilities.SchedulerProvider
import com.kyrylo.kotlinmessenger.utilities.showErrorMessage
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception
import javax.inject.Inject

class LoginPresenter<V : LoginMVPView, I : LoginMVPInteractor> @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable), LoginMVPPresenter<V, I> {

    override fun onPerformMainActivity() {
        getView()?.performMainActivity()
    }

    override fun onPerformRegisterActivity() {
        getView()?.performRegisterActivity()
    }

    override fun onSignIn(context: Context, email: String, password: String) {

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
        }

        getView()?.showProgress()

        interactor?.let {
            it.getSignInInstance(email, password).addOnSuccessListener { _ ->
                Log.d("RegisterActivity", "Finally we saved the user to Firebase Database")

                getView()?.let {
                    it.performMainActivity()
                    it.hideProgress()
                }

            }.addOnFailureListener {
                showErrorMessage(context, it.message!!)
                getView()?.hideProgress()
            }
        }
    }
}
