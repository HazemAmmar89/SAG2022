package com.example.sagapp.screens.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.sagapp.R
import com.example.sagapp.android.BaseFragment
import com.example.sagapp.android.extentions.gone
import com.example.sagapp.android.extentions.navigateSafe
import com.example.sagapp.android.extentions.observe
import com.example.sagapp.authentication.ui.activities.SetupActivity
import com.example.sagapp.authentication.ui.viewmodel.LoginAction
import com.example.sagapp.databinding.FragmentSettingsBinding
import com.example.sagapp.screens.ui.viewmodel.SettingsAction
import com.example.sagapp.screens.ui.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding,SettingsViewModel>() {
    override fun onFragmentReady() {
        binding.SignOutBtn.setOnClickListener {
            mViewModel.logout()

        }
        subscribeToObservers()
    }

    override val mViewModel: SettingsViewModel
        by viewModels()

    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: SettingsAction) {
        when (action) {
            is SettingsAction.FailureMessage -> {
                Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show()
            }
            SettingsAction.Loading -> TODO()
            is SettingsAction.Success -> {
                val myIntent = Intent(this.context, SetupActivity::class.java)
                startActivity(myIntent)
            }
            is SettingsAction.Token -> TODO()
        }
    }
}