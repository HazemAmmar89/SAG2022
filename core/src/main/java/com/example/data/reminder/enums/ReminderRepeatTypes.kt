package com.example.data.reminder.enums

enum class ReminderRepeatTypes {
    ONCE,
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY,
    UNSELECTED
}

fun ReminderRepeatTypes.toString(reminderRepeatTypes: ReminderRepeatTypes) =
    when (reminderRepeatTypes) {
        ReminderRepeatTypes.ONCE -> "Once"
        ReminderRepeatTypes.DAILY -> "Daily"
        ReminderRepeatTypes.WEEKLY -> "Weekly"
        ReminderRepeatTypes.MONTHLY -> "Monthly"
        ReminderRepeatTypes.YEARLY -> "Yearly"
        else -> ""
    }
