package com.example.geofencingproject

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geofencingproject.clockalame.ClockAlarm
import com.example.geofencingproject.clockalame.RecyclerAdapter
import com.example.geofencingproject.geofencing.GeoFencedAlarm
import java.util.*

class MainActivity : AppCompatActivity() {

    private val geofencedAlarms = emptyList<GeoFencedAlarm>()
    private lateinit var timePicker : TimePicker


    private fun openSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            startActivity(Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val clockAlarms = ClockAlarm(applicationContext)

        val recycler = findViewById<RecyclerView>(R.id.recycle_view)
        val adapter = RecyclerAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        adapter.items = listOf( ClockAlarm(applicationContext, "alarm 1"), ClockAlarm(applicationContext, "alarm 2"), GeoFencedAlarm( "geo alarm 3"))


        if (!clockAlarms.canScheduleExactAlarms()) {
            openSettings()
        }


//        findViewById<Button>(R.id.validate_alarm_btn).setOnClickListener {
//            val calendar: Calendar = Calendar.getInstance()
//            calendar.set(
//                calendar.get(Calendar.YEAR),
//                calendar.get(Calendar.MONTH),
//                calendar.get(Calendar.DAY_OF_MONTH),
//                timePicker.hour,
//                timePicker.minute, 0
//            )
//            setAlarm(calendar.timeInMillis)
//        }
    }

    private fun setAlarm(timeInMillis: Long) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, MyAlarm::class.java)
        intent.action = "FOO_ACTION"
        intent.putExtra("KEY_FOO_STRING", "Medium AlarmManager Demo") // TODO: depreciate
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val ALARM_DELAY_IN_SECOND = 3
        val alarmTimeAtUTC = System.currentTimeMillis() + ALARM_DELAY_IN_SECOND * 1_000L

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTimeAtUTC, pendingIntent)

//        alarmManager.setRepeating(AlarmManager.RTC, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
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
