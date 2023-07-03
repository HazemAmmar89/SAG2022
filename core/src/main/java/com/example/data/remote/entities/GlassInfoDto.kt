package com.example.data.remote.entities

import com.google.gson.annotations.SerializedName

data class GlassInfoDto(
    @SerializedName("board_id")
    val boardID: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("id")
    val ID: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("updated_at")
    val updated_at: String

)