package com.fpter.wetalk.features.login.repository

import com.fpter.wetalk.features.ApiConstants
import com.fpter.wetalk.features.login.model.LoginRequest
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApiService {
    @Headers(ApiConstants.Param.JSON_TYPE)
    @POST(ApiConstants.Url.LOGIN)
    fun login(@Body body: LoginRequest): Observable<Any>
}