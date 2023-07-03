package com.example.sagapp.alarm.ui.fragments

import androidx.fragment.app.viewModels
import com.example.data.alarm.AlarmItem
import com.example.sagapp.alarm.ui.viewmodel.AlarmViewModel
import com.example.sagapp.android.BaseFragment
import com.example.sagapp.databinding.FragmentAlarmBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class AlarmFragment : BaseFragment<FragmentAlarmBinding,AlarmViewModel>() {
    override fun onFragmentReady() {

        binding.scheduleButton.setOnClickListener {
            mViewModel.scheduleAlarm(
                AlarmItem(   time = LocalDateTime.now()
                .plusSeconds(binding.secondsText.text.toString().toLong()),binding.message.text.toString())
            )
        }
        binding.cancelButton.setOnClickListener {
            mViewModel.cancelAlarm(
                AlarmItem(   time = LocalDateTime.now()
                .plusSeconds(binding.secondsText.text.toString().toLong()),binding.message.text.toString())
            )
        }
    }

    override val mViewModel: AlarmViewModel by viewModels()


}