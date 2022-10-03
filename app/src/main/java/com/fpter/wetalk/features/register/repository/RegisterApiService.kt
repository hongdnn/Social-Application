package com.fpter.wetalk.features.register.repository

import com.fpter.wetalk.features.ApiConstants
import com.fpter.wetalk.features.register.model.RegisterRequest
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RegisterApiService {
    @Headers(ApiConstants.Param.JSON_TYPE)
    @POST(ApiConstants.Url.REGISTER)
    fun registerUser(@Body body: RegisterRequest): Observable<Any>
}