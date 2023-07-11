package com.example.features.authentication.data.repo

import android.util.Log
import com.example.core.response.BaseResponse
import com.example.data.remote.entities.ChangePasswordDto
import com.example.data.remote.entities.ChangePasswordOtd
import com.example.data.remote.entities.LoginDto
import com.example.data.remote.entities.LoginParams
import com.example.data.remote.entities.LogoutParams
import com.example.data.remote.entities.ReportDto
import com.example.data.remote.entities.ReportOtd
import com.example.data.remote.remote.service.RemoteServices
import javax.inject.Inject

class AuthenticationRepo @Inject constructor(private val authService: RemoteServices) {

    suspend fun login(loginParams: LoginParams) : BaseResponse<LoginDto> {
       return authService.login(loginParams)
    }

    suspend fun changePassword(changePasswordOtd: ChangePasswordOtd): BaseResponse<ChangePasswordDto> {
        return authService.changePassword(changePasswordOtd)
    }

    suspend fun report(reportParams: ReportOtd): BaseResponse<ReportDto> {
        return authService.report(reportParams)
    }

    suspend fun logout(logoutParams: LogoutParams): BaseResponse<String> {
        Log.e("hazem", "logout: ${logoutParams.token}  ${authService.logout(logoutParams)}", )
        return authService.logout(logoutParams)
    }
}