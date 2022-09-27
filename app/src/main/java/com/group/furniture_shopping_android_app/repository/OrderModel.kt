package com.group.furniture_shopping_android_app.repository

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "order_table")
data class OrderModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val order_number: Int,
    val order_date: String,
    val order_total_amount: Double,
    val order_status: String
)
