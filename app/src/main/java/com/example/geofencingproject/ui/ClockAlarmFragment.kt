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
import android.util.Log
import android.widget.Button
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.geofencingproject.MainActivity
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.validateAlarmBtn.setOnClickListener {
            Log.d("ClockAlarmFragment", "set alarm button pressed")

            val clockAlam = ClockAlarm(requireActivity().applicationContext)
            clockAlam.setTriggertime(binding.timePicker.hour, binding.timePicker.minute)
            activity?.supportFragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            MainActivity.adapter.items.plus(clockAlam)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = ClockAlarmFragment()
    }
}
