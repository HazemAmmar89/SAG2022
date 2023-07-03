package com.example.data.remote.entities

data class CashPaymentInfo(
    val address: String,
    val code: String,
    val country: String,
    val created_at: String,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val quantity: String,
    val relationship: String,
    val type: String,
    val updated_at: String
)