package com.example.geofencingproject.clockalame

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
            Toast.makeText(context, "RIIIING", Toast.LENGTH_LONG).show()

    }
}