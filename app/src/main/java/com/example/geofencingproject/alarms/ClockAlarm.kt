package com.example.geofencingproject.alarms

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import java.util.*

const val EXACT_ALARM_INTENT_REQUEST_CODE = 1001

class ClockAlarm(private val context: Context, name: String = "default name") : Alarm(name) {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private var alarmTime : Long = 0


    init {
        super.name = name
    }
    fun setExactAlarm(timeInMillis: Long) {
        val pendingIntent = createExactAlarmIntent()
        val alarmClockInfo = AlarmManager.AlarmClockInfo(timeInMillis, pendingIntent)
        alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)
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

    fun convertToAlarmTimeMillis(hour: Int, minute: Int): Long {
        val calendar = Calendar.getInstance()
        val currentTimeMillis = calendar.timeInMillis
        val proposedTimeMillis = calendar.setHourAndMinute(hour, minute).timeInMillis

        val alarmTimeMillis: Long = if (proposedTimeMillis > currentTimeMillis) {
            proposedTimeMillis
        } else {
            proposedTimeMillis.plusOneDay()
        }

        return alarmTimeMillis
    }

    fun Calendar.setHourAndMinute(hour: Int, minute: Int): Calendar {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)

        return this
    }
    fun Long.plusOneDay(): Long = this + 24 * 60 * 60 * 1000

}