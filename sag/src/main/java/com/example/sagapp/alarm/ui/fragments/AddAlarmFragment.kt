package com.example.sagapp.alarm.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sagapp.R
import com.example.sagapp.alarm.ui.viewmodel.AlarmViewModel
import com.example.sagapp.android.BaseFragment
import com.example.sagapp.android.extentions.navigateSafe
import com.example.sagapp.databinding.FragmentAddAlarmBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddAlarmFragment : BaseFragment<FragmentAddAlarmBinding,AlarmViewModel>() {
    override fun onFragmentReady() {
         binding.addAlarm.setOnClickListener {
             navigateSafe(R.id.action_addAlarmFragment_to_createNewAlarm, container = R.id.frag_host)
         }
    }

    override val mViewModel: AlarmViewModel by viewModels()
}