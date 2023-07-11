package com.example.features.authentication.domain.intractors

import com.example.core.usecase.RemoteUseCase
import com.example.data.remote.entities.LoginDto
import com.example.data.remote.entities.LoginParams
import com.example.data.remote.entities.ReportDto
import com.example.data.remote.entities.ReportOtd
import com.example.features.authentication.data.repo.AuthenticationRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReportUseCase @Inject constructor(private val authenticationRepo: AuthenticationRepo) :
    RemoteUseCase<ReportDto, ReportOtd>() {
    override fun executeRemoteDS(body: ReportOtd?): Flow<ReportDto> = flow {
        emit(authenticationRepo.report(body!!).data!!)
    }

}