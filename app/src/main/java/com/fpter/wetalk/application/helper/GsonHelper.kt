package com.fpter.wetalk.application.helper

import com.google.gson.Gson


class GsonHelper(private val gson: Gson) {
    fun getJsonString(user: Any?): String {
        return gson.toJson(user)
    }

    fun <T> toJsonObject(strJson: String?, type: Class<T>): T? {
        return if (strJson != null) gson.fromJson(strJson, type) else null
    }
}