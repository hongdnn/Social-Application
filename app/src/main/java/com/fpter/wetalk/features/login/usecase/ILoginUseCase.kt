package com.fpter.wetalk.features.login.usecase

import com.fpter.wetalk.features.login.model.LoginRequest
import io.reactivex.rxjava3.core.Observable

interface ILoginUseCase {
    fun login(loginRequest: LoginRequest) : Observable<Any>
}