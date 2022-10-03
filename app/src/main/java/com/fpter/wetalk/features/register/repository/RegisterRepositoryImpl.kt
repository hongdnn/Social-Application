package com.fpter.wetalk.features.register.repository

import com.fpter.wetalk.common.api.BaseRepositoryImpl
import com.fpter.wetalk.features.register.model.RegisterRequest
import io.reactivex.rxjava3.core.Observable

class RegisterRepositoryImpl(service: RegisterApiService) :
    BaseRepositoryImpl<RegisterApiService>(service), IRegisterRepository {
    override fun registerUser(registerRequest: RegisterRequest): Observable<Any> {
        return service.registerUser(registerRequest)
    }
}