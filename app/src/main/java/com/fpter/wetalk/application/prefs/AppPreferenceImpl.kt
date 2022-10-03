package com.fpter.wetalk.application.prefs

import android.content.Context
import android.content.SharedPreferences
import com.fpter.wetalk.application.helper.GsonHelper
import com.fpter.wetalk.features.login.model.User
import timber.log.Timber

class AppPreferenceImpl(context: Context, private var gsonHelper: GsonHelper) : AppPreference {

    companion object {
        private const val PREFS_NAME = "MyApplication"
        private const val KEY_USER = "KEY_USER"
    }

    private var mSharedPreferences: SharedPreferences

    init {
        val applicationContext = context.applicationContext
        mSharedPreferences = applicationContext.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }

    private fun getJsonUser(): String? {
        return mSharedPreferences.getString(KEY_USER, null)
    }

    override fun getLoggedUser(): User? {
        return try {
            gsonHelper.toJsonObject(getJsonUser(), User::class.java)
        } catch (e: Exception) {
            Timber.e(e, "ToJsonObject error: " + e.message)
            mSharedPreferences.edit().remove(KEY_USER).apply()
            return null
        }
    }

    override fun setLoggedUser(user: User?) {
        mSharedPreferences.edit()
            .putString(KEY_USER, gsonHelper.getJsonString(user))
            .apply()
    }

    override fun isLoggedIn(): Boolean {
        return false
    }

    override fun logout() {
        mSharedPreferences.edit()
            .remove(KEY_USER)
            .apply()
    }

    override fun getBearerToken(): String? {
        TODO("Not yet implemented")
    }
}