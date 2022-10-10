package com.group.furniture_shopping_android_app.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications_table")
data class NotificationModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val message: String,
    val order_number: Int,
    val image: String
)

