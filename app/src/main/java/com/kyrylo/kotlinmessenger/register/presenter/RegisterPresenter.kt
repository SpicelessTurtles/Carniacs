package com.kyrylo.kotlinmessenger.register.presenter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.kyrylo.kotlinmessenger.base.presenter.BasePresenter
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import com.kyrylo.kotlinmessenger.register.interactor.RegisterMVPInteractor
import com.kyrylo.kotlinmessenger.register.view.RegisterMVPView
import com.kyrylo.kotlinmessenger.utilities.SchedulerProvider
import com.kyrylo.kotlinmessenger.utilities.showErrorMessage
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RegisterPresenter<V : RegisterMVPView, I : RegisterMVPInteractor> @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable), RegisterMVPPresenter<V, I> {
    override fun onAttach(view: V?) {
        super.onAttach(view)
        hideProgress()
    }

    override fun hideProgress() {
        getView()?.hideProgress()
    }

    override fun showProgress() {
        getView()?.showProgress()
    }

    override fun onPerformRegister(context: Context, email: String, password: String) {

        interactor?.let { inter ->
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
                return
            }

            showProgress()

            inter.createNewUserReference(email,password)
                    .addOnCompleteListener {
                        if (!it.isSuccessful) return@addOnCompleteListener

                        Log.d("Main", it.result.user.uid)

                        uploadImageToFirebaseStorage(context)
                    }
                    .addOnFailureListener {
                        Log.d("Main", "Failed to create user: ${it.message}")

                        showErrorMessage(context, it.message!!)

                        hideProgress()
                    }
        }
    }

    override fun onOpenLoginActivity() {
        getView()?.openLoginActivity()
    }

    override fun onOpenImageChooserActivity() {
        getView()?.openImageChooserActivity()
    }

    override fun onImageChosen(requestCode: Int, resultCode: Int, data: Intent?) {
        getView()?.imageChosen(requestCode, resultCode, data)
    }

    private fun uploadImageToFirebaseStorage(context: Context) {
        interactor?.let { reference ->

        if (getView()?.getSelectedPhoto() == null) {
            showErrorMessage(context, "Choose avatar, you bloody idiot!")
            showProgress()
            return
        }

            reference.getImageStorageReference().putFile(getView()?.getSelectedPhoto()!!)
                    .addOnSuccessListener {
                        Log.d("Register", "Successfully uploaded images:${it.metadata?.path}")

                        reference.getImageStorageReference().downloadUrl.addOnSuccessListener {

                            Log.d("Register Activity", "File Location: $it")

                            saveUserToDatabase(context, it.toString())

                            getView()?.openMainActivity()

                        }
                    }.addOnFailureListener {
                        showErrorMessage(context, it.message!!)
                        hideProgress()
                    }
        }
    }

    private fun saveUserToDatabase(context: Context, profileImageUri: String) {
        interactor?.let {
            val ref = it.getUserDatabaseReference()
            val user = User(it.getUserUID(), getView()?.getRegisterUserName()!!, profileImageUri)

            ref.setValue(user)
                    .addOnSuccessListener {
                        hideProgress()
                        Log.d("RegisterActivity", "Finally we saved the user to Firebase Database")

                    }.addOnFailureListener {
                        showErrorMessage(context, it.message!!)
                        hideProgress()
                    }
        }
    }

    override fun onOpenMainActivity() {
        getView()?.openMainActivity()
    }
}
