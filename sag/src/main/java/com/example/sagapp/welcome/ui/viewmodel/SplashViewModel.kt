package com.example.sagapp.welcome.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.data.welcom.data.OnBoardPref
import com.example.features.authentication.data.StoreData
import com.example.sagapp.android.Action
import com.example.sagapp.android.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

sealed class SplashAction : Action {
    object TimeDone : SplashAction()
    data class NextScreen(val finished: Int) : SplashAction()
}

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val pref: OnBoardPref,
    private val storeToken: StoreData
) : BaseViewModel<SplashAction>() {

    fun splashFinished() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                delay(3000)
                loginFinished()
                onBoardingFinished()
            }


        }
    }

    private fun onBoardingFinished() {
        pref.onBoardReadToFinish().onEach {
            if (!it)
                produce(SplashAction.NextScreen(0))
        }.launchIn(viewModelScope)
    }

    private fun loginFinished() {
        storeToken.onTokenRead().onEach {
            if (it.isNotEmpty())
                produce(SplashAction.NextScreen(2))
            else
                produce(SplashAction.NextScreen(1))
        }.launchIn(viewModelScope)
    }

}
