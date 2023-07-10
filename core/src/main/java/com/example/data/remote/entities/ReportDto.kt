package com.example.data.remote.entities

data class ReportDto(
    val code: String,
    val created_at: String,
    val email: String,
    val id: Int,
    val problem: String,
    val updated_at: String
)