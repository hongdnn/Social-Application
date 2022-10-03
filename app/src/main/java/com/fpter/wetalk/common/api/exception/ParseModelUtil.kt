package com.fpter.wetalk.common.api.exception

import com.google.gson.Gson
import retrofit2.HttpException
import timber.log.Timber

fun parseError(throwable: Throwable): ErrorModel? {
    var exception: ErrorModel? = null
    if (throwable is HttpException && throwable.code() == 400) {
        exception = parseJson(throwable.response()?.errorBody()?.string())
    }
    return exception
}

private fun parseJson(jsonStr: String?): ErrorModel? {
    return try {
        Gson().fromJson(jsonStr, ErrorModel::class.java)
    } catch (e: Exception) {
        Timber.e(e)
        null
    }
}