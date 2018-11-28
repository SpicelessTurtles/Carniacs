package com.kyrylo.kotlinmessenger.register.interactor

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import com.kyrylo.kotlinmessenger.base.interactor.MVPInteractor

interface RegisterMVPInteractor : MVPInteractor{
    fun getImageStorageReference() : StorageReference
    fun getUserDatabaseReference() : DatabaseReference
    fun getUserUID() : String
    fun createNewUserReference(email : String, password : String) : Task<AuthResult>
}