package com.example.sagapp.welcome.ui.viewmodel



import androidx.lifecycle.viewModelScope
import com.example.sagapp.android.Action
import com.example.sagapp.android.BaseViewModel
import com.example.data.welcom.data.OnBoardPref
import com.example.data.welcom.data.OnBoardingData
import com.example.data.welcom.model.OnBoardingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class OnBoardingAction : Action {
    data class OnBoardingData(val list: List<OnBoardingModel>) : OnBoardingAction()

}

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val onBoardData: OnBoardingData, private val pref: OnBoardPref) : BaseViewModel<OnBoardingAction>() {


    fun getOnBoarding(){
        produce(OnBoardingAction.OnBoardingData(onBoardData.welcomeData()))
    }
    fun onBoardingWriteToFinish(){
        viewModelScope.launch {
            pref.onBoardWriteToFinish()
        }
    }

}