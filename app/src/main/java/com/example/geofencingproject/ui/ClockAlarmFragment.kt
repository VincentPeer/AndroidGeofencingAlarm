package com.example.geofencingproject.ui

import com.example.geofencingproject.databinding.FragmentClockAlarmBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.geofencingproject.MainActivity
import com.example.geofencingproject.alarms.entities.ClockAlarm

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
            clockAlam.setTriggerTimeFromPicker(binding.timePicker.hour, binding.timePicker.minute)
            MainActivity.adapter.items = MainActivity.adapter.items.plus(clockAlam)

            activity?.supportFragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        binding.validateAlarmBtn.setOnLongClickListener {
            Log.d("ClockAlarmFragment", "set alarm button long pressed")

            activity?.supportFragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            true// TODO
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = ClockAlarmFragment()
    }
}
