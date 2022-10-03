package com.fpter.wetalk.features.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.fpter.wetalk.application.prefs.AppPreference
import com.fpter.wetalk.common.api.exception.InvalidTokenException
import com.fpter.wetalk.common.base.BaseViewModel
import com.fpter.wetalk.features.login.model.LoginRequest
import com.fpter.wetalk.features.login.usecase.ILoginUseCase
import timber.log.Timber
import java.util.concurrent.TimeUnit

class LoginViewModel(
    private val useCase: ILoginUseCase,
    private val appPreference: AppPreference
) : BaseViewModel() {

    val loading = MutableLiveData(false)
    val loginResult = MutableLiveData<String>()

    fun login(email: String, password: String) {
        loading.value = true
        val loginRequest = LoginRequest(email, password)

        addDisposable(useCase.login(loginRequest)
            .doOnTerminate { loading.value = false }
            .delaySubscription(1, TimeUnit.SECONDS)
            .subscribe({
                loginResult.value = "Success"
            }, { e ->
                Timber.e(e)
                if (e is InvalidTokenException) {
                    login(email, password)
                } else if(e.message != null) {
                    loginResult.value = "Email or password is incorrect"
                }
            }))
    }
}