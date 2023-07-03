package com.example.features.authentication.domain.intractors

import com.example.core.usecase.RemoteUseCase
import com.example.data.remote.entities.LoginDto
import com.example.data.remote.entities.LoginParams
import com.example.features.authentication.data.repo.AuthenticationRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authenticationRepo: AuthenticationRepo) :
    RemoteUseCase<LoginDto, LoginParams>() {
    override fun executeRemoteDS(body: LoginParams?): Flow<LoginDto> = flow {
        emit(authenticationRepo.login(body!!).data!!)
    }

}