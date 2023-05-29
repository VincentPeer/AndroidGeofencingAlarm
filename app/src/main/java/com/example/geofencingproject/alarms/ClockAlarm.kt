package com.example.geofencingproject.alarms

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build

const val EXACT_ALARM_INTENT_REQUEST_CODE = 1001

class ClockAlarm(private val context: Context, name: String = "default name") : Alarm(name) {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private var alarmTime : Long = 0


    init {
        super.name = name

    }
    fun setAlarm(timeInMillis: Long) {

    }
    fun canScheduleExactAlarms(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            alarmManager.canScheduleExactAlarms()
        } else {
            true
        }
    }

    private fun createExactAlarmIntent(): PendingIntent {
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        return PendingIntent.getBroadcast(
            context,
            EXACT_ALARM_INTENT_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }

}