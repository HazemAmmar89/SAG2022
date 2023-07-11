package com.example.sagapp.alarm.ui.fragments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sagapp.R
import com.example.sagapp.alarm.ui.adapters.AlarmAdapter
import com.example.sagapp.alarm.ui.viewmodel.AlarmActions
import com.example.sagapp.alarm.ui.viewmodel.AlarmViewModel
import com.example.sagapp.android.BaseFragment
import com.example.sagapp.android.extentions.navigateSafe
import com.example.sagapp.android.extentions.observe
import com.example.sagapp.databinding.FragmentAddAlarmBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddAlarmFragment : BaseFragment<FragmentAddAlarmBinding,AlarmViewModel>() {
    @Inject
    lateinit var alarmAdapter: AlarmAdapter
    override fun onFragmentReady() {
        mViewModel.getAllAlarm()
         binding.addAlarm.setOnClickListener {
             navigateSafe(R.id.action_addAlarmFragment_to_createNewAlarm, container = R.id.frag_host)
         }
        subscribeToObservers()
    }

    override val mViewModel: AlarmViewModel by viewModels()

    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: AlarmActions) {
        when(action) {
            is AlarmActions.AllAlarm -> {
                binding.notesRV.apply {
                    alarmAdapter.alarms = action.success
                    adapter = alarmAdapter
                    layoutManager = LinearLayoutManager(activity)
                }
            }
            AlarmActions.Cancel -> TODO()
            AlarmActions.Schedule -> TODO()
            is AlarmActions.Success -> TODO()
        }
    }
}