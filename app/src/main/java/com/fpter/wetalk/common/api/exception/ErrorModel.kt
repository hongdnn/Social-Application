package com.fpter.wetalk.common.api.exception

import com.google.gson.annotations.SerializedName

data class ErrorModel(
    @SerializedName("message")
    override val message: String,

    @SerializedName("status")
    val status: String
) : Throwable()