package com.example.sagapp.welcome.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import com.example.sagapp.android.BaseActivity
import com.example.sagapp.databinding.ActivitySplashBinding
import com.example.sagapp.welcome.ui.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override val mViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}