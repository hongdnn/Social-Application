package com.fpter.wetalk.features.login.repository

import com.fpter.wetalk.features.login.model.LoginRequest
import io.reactivex.rxjava3.core.Observable


interface ILoginRepository {
    fun login(loginRequest: LoginRequest) : Observable<Any>
}