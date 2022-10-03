package com.fpter.wetalk.common.base

import com.fpter.wetalk.application.prefs.AppPreference
import com.fpter.wetalk.common.api.exception.parseError
import com.fpter.wetalk.common.api.exception.InvalidTokenException
import com.fpter.wetalk.common.rxbus.event.ErrorEvent
import com.fpter.wetalk.features.ApiConstants
import com.fpter.wetalk.features.refreshtoken.model.RefreshTokenRequest
import com.fpter.wetalk.features.refreshtoken.model.RefreshTokenResponse
import com.fpter.wetalk.features.refreshtoken.repository.RefreshTokenServiceApi
import com.hwangjr.rxbus.RxBus
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException
import timber.log.Timber
import java.io.EOFException

open class BaseUsecase : KoinComponent {

    val refreshTokenServiceApi: RefreshTokenServiceApi by inject()
    val appPreference: AppPreference by inject()

    protected fun <T> requestAsync(rawObservable: Observable<T>): Observable<T> {
        return rawObservable.onErrorResumeNext { throwable: Throwable? ->
            if (throwable is EOFException) return@onErrorResumeNext Observable.empty()
            if (throwable is HttpException) {
                if (throwable.code() == ApiConstants.HttpCode.UNAUTHORIZED) {
                    return@onErrorResumeNext Observable.create { emitter ->
                        appPreference.getLoggedUser()?.let { loggedUser ->
                            refreshTokenServiceApi.refreshToken(RefreshTokenRequest(loggedUser.refreshToken))
                                .subscribe(object : DisposableObserver<RefreshTokenResponse>() {
                                    override fun onNext(refreshTokenResponse: RefreshTokenResponse) {
                                        Timber.d("Before refresh: $loggedUser")
                                        loggedUser.refreshToken = refreshTokenResponse.refreshToken
                                        loggedUser.token = refreshTokenResponse.accessToken
                                        appPreference.setLoggedUser(loggedUser)
                                        Timber.d("After refresh: $loggedUser")
                                        emitter.onError(InvalidTokenException(false))
                                    }

                                    override fun onError(e: Throwable) {
                                        appPreference.logout()
                                        emitter.onError(InvalidTokenException(true))
                                    }

                                    override fun onComplete() {}

                                })
                        }
                    }
                }
            }
            val errorModel = parseError(throwable!!)
            RxBus.get().post(ErrorEvent(errorModel))
            Observable.error(errorModel ?: throwable)
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}

