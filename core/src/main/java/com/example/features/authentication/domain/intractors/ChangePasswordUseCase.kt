package com.example.features.authentication.domain.intractors

import android.util.Log
import com.example.core.response.BaseResponse
import com.example.core.usecase.RemoteUseCase
import com.example.data.remote.entities.ChangePasswordDto
import com.example.data.remote.entities.ChangePasswordOtd
import com.example.features.authentication.data.repo.AuthenticationRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(private val authenticationRepoImpl: AuthenticationRepo) : RemoteUseCase<ChangePasswordDto, String>() {
    override fun executeRemoteDS(body: String?): Flow<ChangePasswordDto> = flow{
        Log.d("changepasswordusecase", "executeRemoteDS: ${authenticationRepoImpl.changePassword(ChangePasswordOtd(body!!)).data!!}")
        emit( authenticationRepoImpl.changePassword(ChangePasswordOtd( body!!)).data!!)
    }

}