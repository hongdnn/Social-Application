package com.fpter.wetalk.features.register.usecase

import com.fpter.wetalk.common.base.BaseUsecase
import com.fpter.wetalk.features.register.model.RegisterRequest
import com.fpter.wetalk.features.register.repository.IRegisterRepository
import io.reactivex.rxjava3.core.Observable

class RegisterUseCaseImpl(private val repository: IRegisterRepository) : BaseUsecase(), IRegisterUseCase {
    override fun registerUser(registerRequest: RegisterRequest): Observable<Any> {
        return requestAsync(repository.registerUser(registerRequest))
    }
}