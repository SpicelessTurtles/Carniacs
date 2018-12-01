package com.kyrylo.kotlinmessenger.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.content.edit
import com.kyrylo.kotlinmessenger.data.preferences.model.User
import com.kyrylo.kotlinmessenger.utilities.AppConstants
import javax.inject.Inject
import com.kyrylo.kotlinmessenger.di.PreferenceInfo
import com.google.gson.Gson
import com.google.android.gms.flags.impl.SharedPreferencesFactory.getSharedPreferences





/**
 * Created by jyotidubey on 04/01/18.
 */
class AppPreferenceHelper @Inject constructor(val context: Context,
                                              @PreferenceInfo private val prefFileName: String) : PreferenceHelper {
    companion object {
        private val PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE"
        private val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"
        private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
        private val PREF_KEY_CURRENT_USER = "PREF_KEY_CURRENT_USER"
        private val PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL"
        private val PREF_KEY_PARTNER= "PREF_KEY_PARTNER"
    }

    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun getCurrentUserLoggedInMode() = mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE, AppConstants.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type)

    override fun getCurrentUserEmail(): String = mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, "abc@gmail.com")

    override fun setCurrentUserEmail(email: String?) = mPrefs.edit { putString(PREF_KEY_CURRENT_USER_EMAIL, email) }

    override fun getAccessToken(): String = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, "")

    override fun setAccessToken(accessToken: String?) = mPrefs.edit { putString(PREF_KEY_ACCESS_TOKEN, accessToken) }

    override fun getCurrentUserId(): Long? {
        val userId = mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX)
        return when (userId) {
            AppConstants.NULL_INDEX -> null
            else -> userId
        }
    }

    override fun cleanSharedPreference() {
        getSharedPreferences(context).edit().clear().apply()
    }

    override fun getCurrentUser(): User? {
        val json = mPrefs.getString(PREF_KEY_CURRENT_USER,"null")
        return Gson().fromJson(json,User::class.java)    }

    override fun setCurrentUser(user: User?){
        val gson = Gson()
        val json = gson.toJson(user)
        mPrefs.edit { putString(PREF_KEY_CURRENT_USER, json) }
    }

    override fun setCurrentUserId(userId: Long?) {
        val id = userId ?: AppConstants.NULL_INDEX
        mPrefs.edit { putLong(PREF_KEY_CURRENT_USER_ID, id) }
    }

    override fun setCurrentUserLoggedInMode(mode: AppConstants.LoggedInMode) {
        mPrefs.edit { putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.type) }
    }

    override fun setPartnerUserName(user: User?) {
        val gson = Gson()
        val json = gson.toJson(user)
        mPrefs.edit{ putString(PREF_KEY_PARTNER, json) }
    }

    override fun getPartnerUser(): User? {
        val json = mPrefs.getString(PREF_KEY_PARTNER,"null")
        return Gson().fromJson(json,User::class.java)
    }

}