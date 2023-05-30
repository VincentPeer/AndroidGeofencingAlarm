package com.example.geofencingproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.geofencingproject.R

class GeofencingAlarmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_geo_alarm, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = GeofencingAlarmFragment()
    }
}
