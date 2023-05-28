package com.example.geofencingproject

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import com.example.geofencingproject.geofencing.GeoFencedAlarm
import java.util.*

class MainActivity : AppCompatActivity() {

    private val clockAlarms = emptyList<AlarmClock>()
    private val geofencedAlarms = emptyList<GeoFencedAlarm>()
    private lateinit var timePicker : TimePicker


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timePicker = findViewById(R.id.time_picker)

        findViewById<Button>(R.id.validate_alarm_btn).setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                timePicker.hour,
                timePicker.minute, 0
            )
            setAlarm(calendar.timeInMillis)
        }
    }

    private fun setAlarm(timeInMillis: Long) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, MyAlarm::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setRepeating(AlarmManager.RTC, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show()
    }


//    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
//        return super.onCreateView(name, context, attrs)
//    }

    private class MyAlarm : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.d("Alarm Bell", "Alarm just fired")
        }
    }
}
