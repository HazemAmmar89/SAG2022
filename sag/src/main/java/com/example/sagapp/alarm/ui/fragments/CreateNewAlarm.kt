package com.example.sagapp.alarm.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sagapp.alarm.ui.viewmodel.AlarmViewModel
import com.example.sagapp.android.BaseFragment
import com.example.sagapp.databinding.FragmentEditAlarmBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNewAlarm : BaseFragment<FragmentEditAlarmBinding,AlarmViewModel> (){
    override fun onFragmentReady() {

    }

    override val mViewModel: AlarmViewModel by viewModels()


}