package com.example.data.remote.entities

import com.google.gson.annotations.SerializedName

data class ChangePasswordOtd (
    @SerializedName("email")
    val email:String
)