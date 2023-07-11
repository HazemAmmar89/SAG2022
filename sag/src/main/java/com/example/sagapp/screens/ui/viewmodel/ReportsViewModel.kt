package com.example.sagapp.screens.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core.response.Resource
import com.example.data.remote.entities.ReportDto
import com.example.data.remote.entities.ReportOtd
import com.example.features.authentication.domain.intractors.ReportUseCase
import com.example.sagapp.android.Action
import com.example.sagapp.android.BaseViewModel
import com.example.sagapp.android.extentions.showLog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class ReportAction : Action {
    object Loading : ReportAction()
    data class FailureMessage(val message: String) : ReportAction()
    data class Token(val message: String) : ReportAction()
    data class Success(val loginInfo: ReportDto) : ReportAction()
}

@HiltViewModel
class ReportsViewModel @Inject constructor(private val reportUseCase: ReportUseCase) :
    BaseViewModel<ReportAction>() {
    fun report(params: ReportOtd) {
        reportUseCase(viewModelScope, params) {
            when (it) {
                is Resource.Failure -> {
                    produce(ReportAction.FailureMessage(it.message.toString()))
                }

                is Resource.Progress -> {
                    it.loading.toString().showLog("loading")
                    if (it.loading)
                        produce(ReportAction.Loading)
                }

                is Resource.Success -> {
                    it.data.toString().showLog("success")
                    produce(ReportAction.Success(it.data!!))
                }
            }
        }
    }
}