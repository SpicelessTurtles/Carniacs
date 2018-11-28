package com.kyrylo.kotlinmessenger.register.interactor

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kyrylo.kotlinmessenger.base.interactor.BaseInteractor
import com.kyrylo.kotlinmessenger.data.preferences.PreferenceHelper
import com.kyrylo.kotlinmessenger.data.preferences.network.ApiHelper
import java.util.*
import javax.inject.Inject

class RegisterInteractor @Inject constructor(private val mContext: Context, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(), RegisterMVPInteractor {


    private val filename by lazy {
        UUID.randomUUID().toString()
    }

   override fun getImageStorageReference() : StorageReference {
        return FirebaseStorage.getInstance().getReference("/images/$filename")
    }

    override fun getUserDatabaseReference() : DatabaseReference {
        return FirebaseDatabase.getInstance().getReference("/users/${getUserUID()}")
    }

    override fun getUserUID(): String =
        FirebaseAuth.getInstance().uid ?: ""

    override fun createNewUserReference(email : String, password : String): Task<AuthResult> =
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)

}