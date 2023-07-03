package com.example.data.remote.entities

import com.google.gson.annotations.SerializedName

data class UserInfoDto(
    @SerializedName("access_token")
    val token: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val ID: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("is_admin")
    val admin: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("oauth_token")
    val oauth_token: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("relationship")
    val relationship: String,
    @SerializedName("updated_at")
    val updated_at: String
)