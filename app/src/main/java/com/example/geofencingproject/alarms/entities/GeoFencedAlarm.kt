package com.example.geofencingproject.alarms.entities

import com.example.geofencingproject.MainActivity
import com.example.geofencingproject.alarms.Alarm


class GeoFencedAlarm(name: String) : Alarm() {
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var radius: Double = 0.0
    var name = "def name geoAlarm ${super.id}"


    fun setAlarm() {
        MainActivity.adapter.items = MainActivity.adapter.items.plus(this)
    }

    init {
        this.name = name
    }
}
