package com.group.furniture_shopping_android_app.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.databinding.ItemNotificationBinding
import com.group.furniture_shopping_android_app.repository.NotificationModel

class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    private lateinit var binding: ItemNotificationBinding
    var notificationList: List<NotificationModel> = emptyList()

    inner class NotificationViewHolder(val binding: ItemNotificationBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val item = notificationList[position]
            binding.tvNotification.text = item.title
            binding.tvNotificationText.text = item.message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(position)
    }


    override fun getItemCount(): Int {
        return notificationList.size
    }

}