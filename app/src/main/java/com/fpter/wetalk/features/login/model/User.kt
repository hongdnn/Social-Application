package com.fpter.wetalk.features.login.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id")
    val id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("birth_day")
    val birthDay: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("token")
    var token: String,
    @SerializedName("refresh_token")
    var refreshToken: String
) {
    init {

    }
}