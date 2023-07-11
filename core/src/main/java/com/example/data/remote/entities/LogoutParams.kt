package com.example.data.remote.entities

import com.google.gson.annotations.SerializedName

data class LogoutParams (
    @SerializedName("access_token")
    val token:String
        )