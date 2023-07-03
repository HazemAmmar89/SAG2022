package com.example.sagapp.authentication.domain.model

data class Login(
    val glassInfo: GlassInfo,
    val stripePaymentInfo: StripePaymentInfo,
    val userInfo: UserInfo
)