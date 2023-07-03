package com.example.sagapp.authentication.domain.model

data class UserInfo(
    val token: String,
    val city: String,
    val created_at: String,
    val email: String,
    val id: Int,
    val img: String,
    val admin: Int,
    val name: String,
    val oauth_token: Any,
    val phone: String,
    val relationship: String,
    val updated_at: String
)