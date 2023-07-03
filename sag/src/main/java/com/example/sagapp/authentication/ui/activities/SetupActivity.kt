package com.example.sagapp.authentication.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sagapp.R
import com.example.sagapp.authentication.ui.viewmodel.LoginViewModel
import com.example.sagapp.ble.ui.BluetoothActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupActivity : AppCompatActivity() {
     private val mViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)
//        mViewModel.login(LoginParams("Hagar@gmail.com","123456"))


    }
}
  