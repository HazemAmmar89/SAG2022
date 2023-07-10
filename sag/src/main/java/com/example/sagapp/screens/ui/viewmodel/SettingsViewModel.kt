package com.example.sagapp.screens.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core.response.Resource
import com.example.data.remote.entities.LogoutParams
import com.example.data.remote.entities.ReportDto
import com.example.data.remote.entities.ReportOtd
import com.example.features.authentication.data.StoreData
import com.example.features.authentication.domain.intractors.LogoutUseCase
import com.example.features.authentication.domain.intractors.ReportUseCase
import com.example.sagapp.android.Action
import com.example.sagapp.android.BaseViewModel
import com.example.sagapp.android.extentions.showLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SettingsAction : Action {
    object Loading : SettingsAction()
    data class FailureMessage(val message: String) : SettingsAction()
    data class Token(val message: String) : SettingsAction()
    data class Success(val loginInfo: Int) : SettingsAction()
}

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val storeToken: StoreData
) :
    BaseViewModel<SettingsAction>() {
    lateinit var token:String

    init {
        storeToken.onTokenRead().onEach {
            token=it
            it.showLog("token is here")
        }.launchIn(viewModelScope)

    }
    fun logout() {
        viewModelScope.launch {
            storeToken.onTokenWrite("")
        }
        logoutUseCase(viewModelScope, LogoutParams(token)) { actions ->
            when (actions) {
                is Resource.Failure -> {
                    actions.message.toString().showLog("fail_hazem")

                    produce(SettingsAction.FailureMessage(actions.message.toString()))
                }

                is Resource.Progress -> {
                    actions.loading.toString().showLog("loading")
                    if (actions.loading)
                        produce(SettingsAction.Loading)
                }

                is Resource.Success -> {
                    if(actions.data == 200) {
                        viewModelScope.launch {
                            storeToken.onTokenWrite("")
                        }
                        storeToken.onTokenRead().onEach {
                            it.showLog("token is here")
                        }.launchIn(viewModelScope)
                        produce(SettingsAction.Success(actions.data))
                    }
                }
            }
        }


    }
}