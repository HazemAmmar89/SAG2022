package com.example.data.remote.remote.service

import com.example.core.response.BaseResponse
import com.example.data.remote.entities.ChangePasswordDto
import com.example.data.remote.entities.ChangePasswordOtd
import com.example.data.remote.entities.LoginDto
import com.example.data.remote.entities.LoginParams
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoteServices {
    companion object {
        private const val LOGIN = "handle-login"
        private const val CHANGE_PASSWORD="changePassword"

    }

    @POST(LOGIN)
    suspend fun login(@Body loginParams: LoginParams): BaseResponse<LoginDto>
    @POST(CHANGE_PASSWORD)
    suspend fun changePassword(@Body changePasswordOtd: ChangePasswordOtd) : BaseResponse<ChangePasswordDto>

}