package com.example.sagapp.authentication.ui.fragments

import androidx.fragment.app.viewModels
import com.example.sagapp.R
import com.example.sagapp.android.BaseFragment
import com.example.sagapp.android.extentions.gone
import com.example.sagapp.android.extentions.navigateSafe
import com.example.sagapp.android.extentions.observe
import com.example.sagapp.android.extentions.visible
import com.example.sagapp.authentication.ui.viewmodel.ForgetPasswordAction
import com.example.sagapp.authentication.ui.viewmodel.ForgetPasswordViewModel
import com.example.sagapp.databinding.FragmentForgetPasswordBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ForgetPasswordFragment :
    BaseFragment<FragmentForgetPasswordBinding, ForgetPasswordViewModel>() {


    override fun onFragmentReady() {

        binding.forgetPasswordFragmentSendBtn.setOnClickListener {
            binding.spinKit.visible()
            mViewModel.forgetPassword(binding.forgetPasswordFragmentEmailInputText.text.toString())
        }
        subscribeToObservers()
    }

    override val mViewModel: ForgetPasswordViewModel by viewModels()
    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: ForgetPasswordAction) {
        when (action) {
            is ForgetPasswordAction.FailureMessage -> {

            }
            ForgetPasswordAction.Loading -> {

            }
            is ForgetPasswordAction.Success -> {
                binding.spinKit.gone()

                navigateSafe(

                    R.id.action_forgetPasswordFragment_to_successfulLoginFragment,
                    container = R.id.frag_host
                )
            }
        }
    }

}