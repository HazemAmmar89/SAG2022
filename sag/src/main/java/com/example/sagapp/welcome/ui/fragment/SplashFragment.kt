package com.example.sagapp.welcome.ui.fragment

import android.util.Log
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import com.example.sagapp.android.BaseFragment
import com.example.sagapp.android.extentions.navigateSafe
import com.example.sagapp.android.extentions.observe
import com.example.sagapp.R
import com.example.sagapp.databinding.FragmentSplashBinding
import com.example.sagapp.welcome.ui.viewmodel.SplashAction
import com.example.sagapp.welcome.ui.viewmodel.SplashViewModel


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override fun onFragmentReady() {
        val slideAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_splashscreen)
        binding.imageSplash.animation = slideAnimation
        mViewModel.splashFinished()
        subscribeToObservers()
    }

    override val mViewModel: SplashViewModel by viewModels()
    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: SplashAction) {
        when (action) {
            SplashAction.TimeDone-> {

            }
            is SplashAction.NextScreen -> {
                Log.e("hazemamaarlogger", "handleUiState: ${action.finished}", )
                when(action.finished){
                    0->{
                        navigateSafe(R.id.actionSplashFragmentToOnBoardingFragment, container = R.id.frag_host)
                    }
                    1->{
                        navigateSafe(R.id.action_splashFragment_to_loginFragment, container = R.id.frag_host)

                    }
                    2->{
                        navigateSafe(R.id.action_splashFragment_to_communicationFragment, container = R.id.frag_host)

                    }
                }

            }



        }
    }

}