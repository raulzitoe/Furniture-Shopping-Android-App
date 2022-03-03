package com.group.furniture_shopping_android_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.databinding.FragmentHomeBinding
import com.group.furniture_shopping_android_app.databinding.FragmentNotificationBinding


class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val notificationList = NotificationModel(context).getNotificationList()
        val recyclerView: RecyclerView = binding.recyclerNotification
        recyclerView.adapter = NotificationAdapter(notificationList)
    }
}