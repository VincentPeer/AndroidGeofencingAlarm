package com.example.geofencingproject

abstract class Alarm(var name: String, val id: Long = ++Alarm.id) {
    companion object {
        private var id = 0L
    }
}