package com.group.furniture_shopping_android_app.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.databinding.ItemNotificationBinding

class NotificationAdapter (private val notificationList: Array<String>) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    private lateinit var binding: ItemNotificationBinding

    class NotificationViewHolder(val binding: ItemNotificationBinding, val notificationList: Array<String>) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.tvNotification.text = notificationList[position]
            binding.tvNotificationText.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis pretium et in arcu adipiscing nec. Turpis pretium et in arcu adipiscing nec. "
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(binding, notificationList)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(position)
    }


    override fun getItemCount(): Int {
        return notificationList.size
    }

}