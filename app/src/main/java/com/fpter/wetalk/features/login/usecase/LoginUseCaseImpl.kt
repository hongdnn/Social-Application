package com.fpter.wetalk.features.login.usecase

import com.fpter.wetalk.common.base.BaseUsecase
import com.fpter.wetalk.features.login.model.LoginRequest
import com.fpter.wetalk.features.login.repository.ILoginRepository
import io.reactivex.rxjava3.core.Observable

class LoginUseCaseImpl(private val repository: ILoginRepository) : BaseUsecase(),
    ILoginUseCase {
    override fun login(loginRequest: LoginRequest): Observable<Any> {
        return requestAsync(repository.login(loginRequest))
    }
}