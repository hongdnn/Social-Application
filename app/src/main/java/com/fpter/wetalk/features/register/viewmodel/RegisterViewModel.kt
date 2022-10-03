package com.fpter.wetalk.features.register.viewmodel

import androidx.lifecycle.MutableLiveData
import com.fpter.wetalk.application.prefs.AppPreference
import com.fpter.wetalk.common.api.exception.InvalidTokenException
import com.fpter.wetalk.common.base.BaseViewModel
import com.fpter.wetalk.features.register.model.RegisterRequest
import com.fpter.wetalk.features.register.usecase.IRegisterUseCase
import timber.log.Timber
import java.util.concurrent.TimeUnit

class RegisterViewModel (
    private val useCase: IRegisterUseCase,
    private val appPreference: AppPreference
) : BaseViewModel() {

    val loading = MutableLiveData(false)
    val updateStatusResult = MutableLiveData<String>()

    fun register(email: String, password: String, firstName: String, lastName: String) {
        loading.value = true
        val registerRequest = RegisterRequest(email, password, firstName, lastName)

        addDisposable(useCase.registerUser(registerRequest)
            .doOnTerminate { loading.value = false }
            .delaySubscription(1, TimeUnit.SECONDS)
            .subscribe({
                updateStatusResult.value = "Success"
            }, { e ->
                Timber.e(e)
                if (e is InvalidTokenException) {
                    register(email, password, firstName, lastName)
                }
            }))

        //addDisposable(RxBus.get().<NewNotification>(Consumer { register(email, password, firstName, lastName) }))
    }
}