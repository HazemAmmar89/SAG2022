package com.example.sagapp.alarm.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sagapp.alarm.ui.viewmodel.AlarmViewModel
import com.example.sagapp.android.BaseFragment
import com.example.sagapp.databinding.FragmentAddAlarmBinding


class AddAlarmFragment : BaseFragment<FragmentAddAlarmBinding,AlarmViewModel>() {
    override fun onFragmentReady() {

    }

    override val mViewModel: AlarmViewModel by viewModels()
}