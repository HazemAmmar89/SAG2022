package com.example.sagapp.alarm.ui.fragments

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sagapp.alarm.ui.adapters.AlarmAdapter
import com.example.sagapp.alarm.ui.viewmodel.AlarmActions
import com.example.sagapp.alarm.ui.viewmodel.AlarmViewModel
import com.example.sagapp.android.BaseFragment
import com.example.sagapp.android.extentions.observe
import com.example.sagapp.databinding.FragmentEditAlarmBinding
import com.example.sagapp.screens.ui.viewmodel.ReportAction
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateNewAlarm : BaseFragment<FragmentEditAlarmBinding,AlarmViewModel> (){


    override fun onFragmentReady() {

    }

    override val mViewModel: AlarmViewModel by viewModels()


}