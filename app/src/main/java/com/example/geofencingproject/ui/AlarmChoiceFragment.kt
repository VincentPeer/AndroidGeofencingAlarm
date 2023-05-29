package com.example.geofencingproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.geofencingproject.R

class AlarmChoiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_alarm_type_choice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.clock_alarm_btn).setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_fragment, ClockAlarmFragment.newInstance())?.commit() // TODO manager les ?.
        }
        view.findViewById<Button>(R.id.geo_alarm_btn).setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_fragment, GeofencingAlarmFragment.newInstance())?.commit() // TODO manager les ?.
        }
    }

    companion object {
        fun newInstance() = AlarmChoiceFragment()
    }
}