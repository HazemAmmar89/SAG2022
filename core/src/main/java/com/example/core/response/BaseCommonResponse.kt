package com.example.core.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
open class BaseCommonResponse(
    @SerializedName("status")
    var status: Int? = null,
    @SerializedName("message")
    var message: String? = null
) : Parcelable