package com.group.furniture_shopping_android_app.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
data class FavoritesModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val price: Double,
    val category: String,
    val image: String
)
