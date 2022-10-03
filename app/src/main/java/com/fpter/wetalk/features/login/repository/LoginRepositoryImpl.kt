package com.fpter.wetalk.features.login.repository

import com.fpter.wetalk.common.api.BaseRepositoryImpl
import com.fpter.wetalk.features.login.model.LoginRequest
import io.reactivex.rxjava3.core.Observable

class LoginRepositoryImpl(service: LoginApiService) :
    BaseRepositoryImpl<LoginApiService>(service), ILoginRepository {
    override fun login(loginRequest: LoginRequest): Observable<Any> {
        return service.login(loginRequest)
    }
}