package com.example.geofencingproject

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geofencingproject.alarms.entities.ClockAlarm
import com.example.geofencingproject.alarms.clock.RecyclerAdapter
import com.example.geofencingproject.alarms.entities.GeoFencedAlarm
import com.example.geofencingproject.ui.AlarmChoiceFragment

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var adapter: RecyclerAdapter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recycle_view)
        adapter = RecyclerAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)

        findViewById<ImageButton>(R.id.create_alarm).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, AlarmChoiceFragment.newInstance())
                .addToBackStack("BackHomeActivity")
                .setReorderingAllowed(true)
                .commit()
        }
    }
    private fun openSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            startActivity(Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM))
        }
    }
}
