package com.fpter.wetalk.features.login.injection

import com.fpter.wetalk.features.login.repository.ILoginRepository
import com.fpter.wetalk.features.login.repository.LoginApiService
import com.fpter.wetalk.features.login.repository.LoginRepositoryImpl
import com.fpter.wetalk.features.login.usecase.ILoginUseCase
import com.fpter.wetalk.features.login.usecase.LoginUseCaseImpl
import com.fpter.wetalk.features.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val loginModule = module {
    viewModel {
        LoginViewModel(get(), get())
    }

    factory<LoginApiService> {
        get<Retrofit>().create(LoginApiService::class.java)
    }

    factory<ILoginRepository> {
        LoginRepositoryImpl(get())
    }

    factory<ILoginUseCase> {
        LoginUseCaseImpl(get())
    }
}