package com.example.geofencingproject

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TimePicker
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geofencingproject.alarms.ClockAlarm
import com.example.geofencingproject.alarms.clock.RecyclerAdapter
import com.example.geofencingproject.alarms.GeoFencedAlarm
import com.example.geofencingproject.ui.AlarmChoiceFragment

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var adapter: RecyclerAdapter
    }


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
        adapter = RecyclerAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        adapter.items = mutableListOf( ClockAlarm(applicationContext, "alarm 1"), ClockAlarm(applicationContext, "alarm 2"), GeoFencedAlarm( "geo alarm 3"))


        findViewById<ImageButton>(R.id.create_alarm).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, AlarmChoiceFragment.newInstance())
                .addToBackStack("BackHomeActivity")
                .setReorderingAllowed(true)
                .commit()
        }


        if (!clockAlarms.canScheduleExactAlarms()) {
            openSettings()
        }
    }
}
