package com.example.geofencingproject.ui

import android.content.Context
import android.content.Intent
import com.example.geofencingproject.databinding.FragmentClockAlarmBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.app.AlarmManager
import android.app.PendingIntent
import android.widget.Button
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.geofencingproject.R
import com.example.geofencingproject.alarms.ClockAlarm
import java.util.*

class ClockAlarmFragment : Fragment() {
    private lateinit var binding: FragmentClockAlarmBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClockAlarmBinding.inflate(inflater, container, false)

        return inflater.inflate(R.layout.fragment_clock_alarm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.validateAlarmBtn.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                binding.timePicker.hour,
                binding.timePicker.minute, 0
            )
            val clockAlam = ClockAlarm(requireContext(),"my alarm")
            val alarmTimeMillis: Long = clockAlam.convertToAlarmTimeMillis(calendar.get(Calendar.HOUR), calendar.get(Calendar.HOUR))
            clockAlam.setExactAlarm(alarmTimeMillis)
        }
    }



    companion object {
        @JvmStatic
        fun newInstance() = ClockAlarmFragment()
    }
}