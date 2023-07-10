package com.example.features.authentication.domain.intractors

import android.util.Log
import com.example.core.usecase.RemoteUseCase
import com.example.data.remote.entities.LogoutParams
import com.example.features.authentication.data.repo.AuthenticationRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val authenticationRepo: AuthenticationRepo) :
    RemoteUseCase<Int, LogoutParams>() {
    override fun executeRemoteDS(body: LogoutParams?): Flow<Int> = flow {
        Log.e("azmi_hazem", "executeRemoteDS: ${authenticationRepo.logout(body!!).message}", )
        emit(authenticationRepo.logout(body!!).status!!)

    }
}