package com.kyrylo.kotlinmessenger.registerlogin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.kyrylo.kotlinmessenger.messages.LatestMessagesActivity
import com.kyrylo.kotlinmessenger.R
import com.kyrylo.kotlinmessenger.models.User
import com.kyrylo.kotlinmessenger.utilities.showErrorMessage
import java.util.*


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        progressBar_register.visibility = GONE


        login_button_register.setOnClickListener {
            performRegister()
        }

        already_have_account_textview.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        select_photo.setOnClickListener {
            Log.d("Main Activity", "Try to show photo selector")

            val intent = Intent(Intent.ACTION_PICK)

            intent.type = "image/*"

            startActivityForResult(intent, 0)
        }

    }

    var selectedPhotoUri: Uri? = null


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d("RegisterActivity", "Photo was selected")
        }

        selectedPhotoUri = data?.data
        if (selectedPhotoUri != null) {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            selectphoto_imageview_register.setImageBitmap(bitmap)
            select_photo.alpha = 0f
            //          val bitmapDrawable = BitmapDrawable(bitmap)
            //          select_photo.setBackgroundDrawable(bitmapDrawable)
        }


    }

    private fun performRegister() {

        val email = email_edittext_register.text.toString()
        val password = password_edittext_register.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
            return
        }

        progressBar_register.visibility = VISIBLE

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener

                    Log.d("Main", "${it.result.user.uid}")

                    uploadImageToFirebaseStorage()
                }
                .addOnFailureListener {
                    Log.d("Main", "Failed to create user: ${it.message}")

                    showErrorMessage(this@RegisterActivity,it.message!!)

                    progressBar_register.visibility = GONE
                }
    }

    private fun uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null){
            showErrorMessage(this@RegisterActivity,"Choose avatar, you bloody idiot!")
            progressBar_register.visibility = GONE
            return
        }

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")


        ref.putFile(selectedPhotoUri!!)
                .addOnSuccessListener {
                    Log.d("Register", "Successfully uploaded images:${it.metadata?.path}")

                    ref.downloadUrl.addOnSuccessListener {
                        Log.d("Register Activity", "File Location: $it")

                        saveUserToFirebaseDatabase(it.toString())
                    }
                }.addOnFailureListener {
                    showErrorMessage(this@RegisterActivity,it.message!!)

                    progressBar_register.visibility = GONE
                }
    }

    private fun saveUserToFirebaseDatabase(profileImageUri: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        val user = User(uid, username_register.text.toString(), profileImageUri)

        ref.setValue(user)
                .addOnSuccessListener {
                    progressBar_register.visibility = GONE
                    Log.d("RegisterActivity", "Finally we saved the user to Firebase Database")

                    val intent = Intent(this, LatestMessagesActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)

                }.addOnFailureListener {
                    showErrorMessage(this@RegisterActivity,it.message!!)

                    progressBar_register.visibility = GONE
                }
    }

}





