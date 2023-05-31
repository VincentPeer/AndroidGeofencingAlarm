package com.example.geofencingproject.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.geofencingproject.alarms.entities.GeoFencedAlarm
import com.example.geofencingproject.databinding.FragmentGeoAlarmBinding

class GeofencingAlarmFragment : Fragment() {
    private lateinit var binding: FragmentGeoAlarmBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGeoAlarmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.saveBtn.setOnLongClickListener {
            Log.d("GeofencingAlarmFragment", "onViewCreated: ${binding.geoAlarmTitleInput.text}")
            val alarm = GeoFencedAlarm(binding.geoAlarmTitleInput.text.toString())
            alarm.setAlarm()
            activity?.supportFragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            true
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = GeofencingAlarmFragment()
    }
}
