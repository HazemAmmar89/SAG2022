package com.example.data.remote.entities

import com.google.gson.annotations.SerializedName

data class LoginParams(
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String
)
