package com.fpter.wetalk.application.prefs

import com.fpter.wetalk.features.login.model.User

interface AppPreference {
    fun getLoggedUser(): User?

    fun setLoggedUser(user: User?)

    fun isLoggedIn(): Boolean

    fun logout()

    fun getBearerToken(): String?
}