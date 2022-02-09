package com.app.criminalintent

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.Calendar
import java.util.Date

private const val ARG_DATE = "date"

class TimePickerFragment : DialogFragment() {

    interface Callbacks {
        fun onTimeSelected(date: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = arguments?.getSerializable(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDay = calendar.get(Calendar.DAY_OF_MONTH)
        val initialMinutes = calendar.get(Calendar.MINUTE)
        val initialHours = calendar.get(Calendar.HOUR_OF_DAY)

        val timeListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            targetFragment?.let { fragment ->
                calendar[Calendar.YEAR] = initialYear
                calendar[Calendar.MONTH] = initialMonth
                calendar[Calendar.DAY_OF_MONTH] = initialDay
                calendar[Calendar.HOUR_OF_DAY] = hourOfDay
                calendar[Calendar.MINUTE] = minute
                val resultDate: Date = calendar.time
                (fragment as Callbacks).onTimeSelected(resultDate)
            }
        }

        return TimePickerDialog(
            requireContext(),
            timeListener,
            initialHours,
            initialMinutes,
            true
        )
    }

    companion object {
        fun newInstance(date: Date): TimePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }
            return TimePickerFragment().apply { arguments = args }
        }
    }
}