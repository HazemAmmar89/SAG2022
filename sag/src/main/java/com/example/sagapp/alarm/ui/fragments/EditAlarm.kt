package com.example.sagapp.alarm.ui.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.sagapp.R



class EditAlarm : Fragment() {

    private lateinit var textView: TextView
    private lateinit var button: Button
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button
    private var selectedTime: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_edit_alarm, container, false)

        textView = rootView.findViewById(R.id.time_tv)
        button = rootView.findViewById(R.id.timepicker)
        saveButton = rootView.findViewById(R.id.setAlarm)
        cancelButton = rootView.findViewById(R.id.cancelAlarm)

        button.setOnClickListener {
            openTimePicker()
        }

        saveButton.setOnClickListener {
            selectedTime = textView.text.toString()

            Toast.makeText(requireContext(), "Time saved: $selectedTime", Toast.LENGTH_SHORT).show()
        }

        cancelButton.setOnClickListener {

            selectedTime = ""

            Toast.makeText(requireContext(), "Time canceled", Toast.LENGTH_SHORT).show()
        }

        return rootView
    }

    private fun openTimePicker() {
        val timePickerDialog = TimePickerDialog(
            requireContext(), R.style.DialogTheme,
            { _, hour, minute ->
                textView.text = "$hour:$minute"
            },
            15, 30, false
        )

        timePickerDialog.show()
    }
}