package com.kyrylo.kotlinmessenger.data.preferences.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.kyrylo.kotlinmessenger.di.ApiKeyInfo
import javax.inject.Inject

/**
 * Created by jyotidubey on 11/01/18.
 */
class ApiHeader @Inject constructor(internal val publicApiHeader: PublicApiHeader, internal val protectedApiHeader: ProtectedApiHeader) {

    class PublicApiHeader @Inject constructor()

    class ProtectedApiHeader @Inject constructor()

}