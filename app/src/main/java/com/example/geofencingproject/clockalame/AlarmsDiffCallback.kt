package com.example.geofencingproject.clockalame

import androidx.recyclerview.widget.DiffUtil
import com.example.geofencingproject.Alarm


class AlarmsDiffCallback(private val oldList: List<Alarm>, private val newList: List<Alarm>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList.get(newItemPosition).id
    }
    override fun areContentsTheSame(oldItemPosition : Int, newItemPosition : Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return old::class == new::class && old.name == new.name
    }
}