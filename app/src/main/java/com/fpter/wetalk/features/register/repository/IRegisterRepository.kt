package com.fpter.wetalk.features.register.repository

import com.fpter.wetalk.features.register.model.RegisterRequest
import io.reactivex.rxjava3.core.Observable


interface IRegisterRepository {
    fun registerUser(registerRequest: RegisterRequest) : Observable<Any>
}