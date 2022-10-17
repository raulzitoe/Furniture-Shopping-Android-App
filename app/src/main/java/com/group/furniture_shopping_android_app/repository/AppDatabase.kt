package com.group.furniture_shopping_android_app.repository

import androidx.room.RoomDatabase
import androidx.room.Database

@Database(entities = [CartModel::class, OrderModel::class, FavoritesModel::class, ShippingAddressModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}