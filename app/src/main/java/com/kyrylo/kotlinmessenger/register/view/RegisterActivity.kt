package com.kyrylo.kotlinmessenger.register.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View.*
import kotlinx.android.synthetic.main.activity_register.*
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.base.view.BaseActivity
import com.kyrylo.kotlinmessenger.login.view.LoginActivity
import com.kyrylo.kotlinmessenger.main.view.MainActivity
import com.kyrylo.kotlinmessenger.register.interactor.RegisterMVPInteractor
import com.kyrylo.kotlinmessenger.register.presenter.RegisterMVPPresenter
import javax.inject.Inject


class RegisterActivity : BaseActivity(), RegisterMVPView {

    @Inject
    internal lateinit var presenter: RegisterMVPPresenter<RegisterMVPView, RegisterMVPInteractor>

    private var selectedPhotoUri: Uri? = null

    override fun getSelectedPhoto(): Uri? = selectedPhotoUri

    override fun getRegisterUserName(): String = username_register.text.toString()

    override fun hideProgress() {
        progressBar_register.visibility = GONE
    }

    override fun showProgress() {
        progressBar_register.visibility = VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()
        presenter.onAttach(this)

        login_button_register.setOnClickListener {
            val email = email_edittext_register.text.toString()
            val password = password_edittext_register.text.toString()

            presenter.onPerformRegister(context = this, email = email, password = password)
        }

        already_have_account_textview.setOnClickListener {
            presenter.onOpenLoginActivity()
        }

        select_photo.setOnClickListener {
            presenter.onOpenImageChooserActivity()
        }

    }

    override fun openImageChooserActivity() {
        Log.d("Main Activity", "Try to show photo selector")

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)
    }

    override fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onImageChosen(requestCode,resultCode,data)
    }

    override fun imageChosen(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d("RegisterActivity", "Photo was selected")
        }

        selectedPhotoUri = data?.data

        if (selectedPhotoUri != null) {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            selectphoto_imageview_register.setImageBitmap(bitmap)
            select_photo.alpha = 0f
        }
    }

    override fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}





