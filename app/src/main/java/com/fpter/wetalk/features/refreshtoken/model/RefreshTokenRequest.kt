package com.fpter.wetalk.features.refreshtoken.model

import com.google.gson.annotations.SerializedName

class RefreshTokenRequest(@SerializedName("refreshToken") val refreshToken: String)