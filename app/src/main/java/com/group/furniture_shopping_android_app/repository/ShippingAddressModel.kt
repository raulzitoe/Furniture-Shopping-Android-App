package com.group.furniture_shopping_android_app.repository

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "shipping_addresses_table")
data class ShippingAddressModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String = "Raul Vieira",
    val street: String = "000 Main St",
    val city: String = "Toronto",
    val zip: String = "000 000",
    val province: String = "Ontario",
    val country: String = "Canada",
    val isDefaultAddress: Boolean = false
)
