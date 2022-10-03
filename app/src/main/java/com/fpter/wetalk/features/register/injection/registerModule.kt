package com.fpter.wetalk.features.register.injection

import com.fpter.wetalk.features.register.repository.IRegisterRepository
import com.fpter.wetalk.features.register.repository.RegisterApiService
import com.fpter.wetalk.features.register.repository.RegisterRepositoryImpl
import com.fpter.wetalk.features.register.usecase.IRegisterUseCase
import com.fpter.wetalk.features.register.usecase.RegisterUseCaseImpl
import com.fpter.wetalk.features.register.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val registerModule = module {
    //scope(named<RegisterActivity>()) {
        viewModel {
            RegisterViewModel(get(), get())
        }

        factory<RegisterApiService> {
            get<Retrofit>().create(RegisterApiService::class.java)
        }

        factory<IRegisterRepository> {
            RegisterRepositoryImpl(get())
        }

        factory<IRegisterUseCase> {
            RegisterUseCaseImpl(get())
        }
   // }
}