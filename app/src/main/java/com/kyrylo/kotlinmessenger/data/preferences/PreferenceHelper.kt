package com.kyrylo.kotlinmessenger.data.preferences

import com.kyrylo.kotlinmessenger.data.preferences.model.User
import com.kyrylo.kotlinmessenger.utilities.AppConstants

/**
 * Created by jyotidubey on 04/01/18.
 */
interface PreferenceHelper {

    fun getCurrentUserLoggedInMode(): Int

    fun setCurrentUserLoggedInMode(mode: AppConstants.LoggedInMode)

    fun getCurrentUserId(): Long?

    fun setCurrentUserId(userId: Long?)

    fun getCurrentUser(): User?

    fun setCurrentUser(user: User?)

    fun getCurrentUserEmail(): String?

    fun setCurrentUserEmail(email: String?)

    fun getAccessToken(): String?

    fun setAccessToken(accessToken: String?)

    fun setPartnerUserName(user: User?)

    fun getPartnerUser():User?

}