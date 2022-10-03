package com.fpter.wetalk.features.refreshtoken.repository

import com.fpter.wetalk.features.ApiConstants
import com.fpter.wetalk.features.refreshtoken.model.RefreshTokenRequest
import com.fpter.wetalk.features.refreshtoken.model.RefreshTokenResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RefreshTokenServiceApi {
    @Headers(ApiConstants.Param.JSON_TYPE)
    @POST(ApiConstants.Url.REFRESH_TOKEN)
    fun refreshToken(@Body body: RefreshTokenRequest): Observable<RefreshTokenResponse>
}