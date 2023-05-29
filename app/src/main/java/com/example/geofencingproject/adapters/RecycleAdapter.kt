package com.example.geofencingproject.alarms.clock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.geofencingproject.alarms.Alarm
import com.example.geofencingproject.R
import com.example.geofencingproject.alarms.AlarmsDiffCallback
import com.example.geofencingproject.alarms.ClockAlarm

class RecyclerAdapter(_items : List<Alarm> = listOf()) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    var items = listOf<Alarm>()
        set(value) {
            val diffCallback = AlarmsDiffCallback(items, value)
            val diffItems = DiffUtil.calculateDiff(diffCallback)
            field = value
            diffItems.dispatchUpdatesTo(this)
        }

    init { items = _items }
    override fun getItemCount() = items.size
    override fun getItemViewType(position: Int): Int {
        return if(items[position] is ClockAlarm) CLOCK_ALARM
        else GEO_ALARM
    }

    companion object {
        private const val CLOCK_ALARM = 1
        private const val GEO_ALARM = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType) {
            CLOCK_ALARM -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_clock_alarm, parent, false))
            else /* GEO_ALARM */ -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_geo_alarm, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameClockAlarm = view.findViewById<TextView>(R.id.list_item_clock_alarm_name)
        private val nameGeoAlarm = view.findViewById<TextView>(R.id.list_item_geo_alarm_name)
        fun bind(alarm : Alarm) {
            if(alarm is ClockAlarm) nameClockAlarm?.text = alarm.name
            else nameGeoAlarm?.text = alarm.name
        }
    }

}