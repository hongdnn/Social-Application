package com.fpter.wetalk.features.refreshtoken.model

import com.google.gson.annotations.SerializedName

class RefreshTokenResponse(
        @SerializedName("accessToken") val accessToken: String,
        @SerializedName("refreshToken") val refreshToken: String
)