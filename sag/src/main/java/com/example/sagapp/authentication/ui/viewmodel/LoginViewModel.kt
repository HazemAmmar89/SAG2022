package com.example.sagapp.authentication.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core.response.Resource
import com.example.data.remote.entities.LoginDto
import com.example.data.remote.entities.LoginParams
import com.example.features.authentication.data.StoreData
import com.example.features.authentication.domain.intractors.LoginUseCase
import com.example.sagapp.android.Action
import com.example.sagapp.android.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LoginAction : Action {
    object Loading : LoginAction()
    data class FailureMessage(val message:String):LoginAction()
    data class Token(val message:String):LoginAction()
    data class Success(val loginInfo: LoginDto):LoginAction()
}
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase,private val storeToken:StoreData) : BaseViewModel<LoginAction>() {

    fun login(params: LoginParams){
        loginUseCase(viewModelScope,params){
            when(it){
                is Resource.Failure -> {
                    produce(LoginAction.FailureMessage(it.message.toString()))
                }
                is Resource.Progress -> {
                    if(it.loading)
                        produce(LoginAction.Loading)
                }
                is Resource.Success -> {
                    produce(LoginAction.Success(it.data))
                    viewModelScope.launch {
                        storeToken.onTokenWrite(it.data.UserDto.token)
                    }
                }
            }

        }
    }
    fun checkToken(){
        storeToken.onTokenRead().onEach {
        if (it.isNotEmpty())
            produce(LoginAction.Token(it))
        }.launchIn(viewModelScope)
    }
}