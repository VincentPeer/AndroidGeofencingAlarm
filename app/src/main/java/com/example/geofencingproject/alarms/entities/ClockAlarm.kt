package com.example.geofencingproject.alarms.entities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.geofencingproject.MainActivity
import com.example.geofencingproject.alarms.Alarm
import com.example.geofencingproject.alarms.AlarmBroadcastReceiver
import java.text.SimpleDateFormat
import java.util.*

const val EXACT_ALARM_INTENT_REQUEST_CODE = 1001

class ClockAlarm(private val context: Context) : Alarm() {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private lateinit var triggerTime: Calendar


    fun setExactAlarm(timeInMillis: Long) {
        val pendingIntent = createExactAlarmIntent()

        // For exact alarm, the two following lines can be replaced by the line just below
        // alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
        val alarmClockInfo = AlarmManager.AlarmClockInfo(timeInMillis, pendingIntent)
        alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)
    }

    fun setTriggerTimeFromPicker(hour: Int, minute: Int) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            hour,
            minute,
            0
        )
        triggerTime = calendar
    }

    fun setTriggerTimeFromMillis(millis: Long) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        triggerTime = calendar
        setExactAlarm(millis)
    }

    fun getTriggerTime(): String {
        return SimpleDateFormat.getDateTimeInstance().format(triggerTime.time).toString()
    }

    fun setMultipleAlarmsOnLongPress() {
        val ac1 = ClockAlarm(context)
        ac1.setTriggerTimeFromMillis(1690804294000) // 31.07.2023 13h51
        val ac2 = ClockAlarm(context)
        ac2.setTriggerTimeFromMillis(1693482694000) // 31.08.2023 13h51
        val ac3 = ClockAlarm(context)
        ac3.setTriggerTimeFromMillis(1694778694000) // 15.09.2023 13h51
        val ac4 = ClockAlarm(context)
        ac4.setTriggerTimeFromMillis(1697370694000) // 15.10.2023 13h51

        MainActivity.adapter.items = MainActivity.adapter.items.plus(ac1).plus(ac2).plus(ac3).plus(ac4)

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