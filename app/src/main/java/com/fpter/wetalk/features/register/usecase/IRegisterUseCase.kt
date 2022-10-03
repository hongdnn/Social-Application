package com.fpter.wetalk.features.register.usecase

import com.fpter.wetalk.features.register.model.RegisterRequest
import io.reactivex.rxjava3.core.Observable

interface IRegisterUseCase {
    fun registerUser(registerRequest: RegisterRequest) : Observable<Any>
}