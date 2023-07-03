package com.example.data.remote.entities

import com.google.gson.annotations.SerializedName

data class LoginDto(
    @SerializedName("CashPaymentInfo")
    val cashPaymentDto: CashPaymentInfo,
    @SerializedName("GlassInfo")
    val glassDto: GlassInfoDto,
    @SerializedName("StripePaymentInfo")
    val StripePaymentDto: StripePaymentInfoDto,
    @SerializedName("UserInfo")
    val UserDto: UserInfoDto
)