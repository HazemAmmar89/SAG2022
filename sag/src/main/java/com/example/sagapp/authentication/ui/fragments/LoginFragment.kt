package com.example.sagapp.authentication.ui.fragments

import android.graphics.Color
import android.util.Log
import androidx.fragment.app.viewModels
import com.example.data.remote.entities.LoginParams
import com.example.sagapp.R
import com.example.sagapp.android.BaseFragment
import com.example.sagapp.android.extentions.gone
import com.example.sagapp.android.extentions.navigateSafe
import com.example.sagapp.android.extentions.observe
import com.example.sagapp.android.extentions.visible
import com.example.sagapp.authentication.ui.viewmodel.LoginAction
import com.example.sagapp.authentication.ui.viewmodel.LoginViewModel
import com.example.sagapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun onFragmentReady() {

        subscribeToObservers()
        mViewModel.checkToken()
        binding.loginFragmentConfirmBtn.setOnClickListener {
            binding.spinKit.visible()
            mViewModel.login(
                LoginParams(
                    binding.loginFragmentEmailInputText.text.toString(),
                    binding.loginFragmentPasswordInputText.text.toString()
                )
            )

        }

        binding.loginFragmentForgetPasswordTxt.setOnClickListener {
            navigateSafe(
                R.id.action_loginFragment_to_forgetPasswordFragment,
                container = R.id.frag_host
            )
        }
    }


    override val mViewModel: LoginViewModel by viewModels()
    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: LoginAction) {
        when (action) {
            is LoginAction.FailureMessage -> {
               binding.loginFragmentEmailInputText.error = "email is not correct"
               binding.loginFragmentEmailInputTextLayout.boxStrokeColor = Color.RED
                binding.spinKit.gone()

            }
            LoginAction.Loading -> {
                Log.e("hhh", "loading")
            }
            is LoginAction.Success -> {
                binding.spinKit.gone()
                navigateSafe(
                    R.id.action_loginFragment_to_screensActivity,
                    container = R.id.frag_host
                )
            }

        }
    }


}

