package com.example.sagapp.screens.ui.fragments


import android.graphics.Color
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.data.remote.entities.ReportOtd
import com.example.sagapp.R
import com.example.sagapp.android.BaseFragment
import com.example.sagapp.android.extentions.gone
import com.example.sagapp.android.extentions.navigateSafe
import com.example.sagapp.android.extentions.observe
import com.example.sagapp.authentication.ui.viewmodel.LoginAction
import com.example.sagapp.databinding.FragmentReportsBinding
import com.example.sagapp.screens.ui.viewmodel.ReportAction
import com.example.sagapp.screens.ui.viewmodel.ReportsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReportsFragment : BaseFragment<FragmentReportsBinding, ReportsViewModel>() {
    override fun onFragmentReady() {

        subscribeToObservers()
        binding.reportFragmentSubmitBtn.setOnClickListener {
            mViewModel.report(
                ReportOtd(
                    binding.reportFragmentEmailInputText.text.toString(),
                    binding.reportFragmentCodeSagInputText.text.toString(),
                    binding.reportFragmentProblemInputText.text.toString()
                )
            )

        }
    }

    override val mViewModel: ReportsViewModel by viewModels()
    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: ReportAction) {
        when(action) {
            is ReportAction.FailureMessage -> {


            }
            ReportAction.Loading -> {

            }
            is ReportAction.Success -> {
                Toast.makeText(context, "Successful Send Report", Toast.LENGTH_SHORT).show()
            }
            is ReportAction.Token -> {

            }
        }
    }

}