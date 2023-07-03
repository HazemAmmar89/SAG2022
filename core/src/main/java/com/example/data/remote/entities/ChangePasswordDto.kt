package com.example.data.remote.entities

data class ChangePasswordDto(
    val id: Int,
    val name: String,
    val email: String,
    val is_admin: Int,
    val relationship: String,
    val phone: String,
    val city: String,
    val img: String,
    val access_token: String,
    val oauth_token: String,
    val created_at: String,
    val updated_at: String
)