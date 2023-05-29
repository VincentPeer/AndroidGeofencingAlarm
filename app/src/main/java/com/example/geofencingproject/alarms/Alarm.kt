package com.example.geofencingproject.alarms

abstract class Alarm(var name: String, val id: Long = ++Companion.id) {
    companion object {
        private var id = 0L
    }
}