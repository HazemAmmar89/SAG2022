package com.example.data.reminder

import com.example.data.reminder.enums.ReminderRepeatTypes
import java.util.*

data class ReminderModel(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val repeat: ReminderRepeatTypes =ReminderRepeatTypes.ONCE,
    val date: Calendar,
)
