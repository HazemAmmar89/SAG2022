package com.example.sagapp.alarm.ui.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sagapp.R
import com.example.sagapp.alarm.ui.viewmodel.AlarmViewModel
import com.example.sagapp.android.BaseFragment
import com.example.sagapp.android.extentions.showLog
import com.example.sagapp.databinding.FragmentEditAlarmBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditAlarm.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class EditAlarm : BaseFragment<FragmentEditAlarmBinding,AlarmViewModel>(){
    override fun onFragmentReady() {
        binding.timepicker.setOnClickListener {
            var cal = Calendar.getInstance()
            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd.MM.yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                // Display Selected date in textbox
                Toast.makeText(requireContext(),sdf.format(cal.time), Toast.LENGTH_LONG).show()

            },2015,6,20)
        }

        binding.setAlarm.setOnClickListener {
            val selectedTime = binding.timeTv.text.toString()

            Toast.makeText(requireContext(), "Time saved: $selectedTime", Toast.LENGTH_SHORT).show()
        }

        binding.cancelAlarm.setOnClickListener {

            val selectedTime = ""

            Toast.makeText(requireContext(), "Time canceled", Toast.LENGTH_SHORT).show()
        }
    }

    override val mViewModel: AlarmViewModel
        get() = TODO("Not yet implemented")



    private fun openTimePicker() {
        val timePickerDialog = TimePickerDialog(
            requireContext(), R.style.DialogTheme,
            { _, hour, minute ->
                binding.timeTv.text = "$hour:$minute"
            },
            15, 30, false
        )

        timePickerDialog.show()
    }
}