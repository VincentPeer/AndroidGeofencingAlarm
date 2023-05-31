package com.example.geofencingproject.alarms

import androidx.recyclerview.widget.DiffUtil
import com.example.geofencingproject.alarms.Alarm


class AlarmsDiffCallback(private val oldList: List<Alarm>, private val newList: List<Alarm>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition : Int, newItemPosition : Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return old::class == new::class
    }
}