package com.group.furniture_shopping_android_app.notification

import android.content.Context
import com.group.furniture_shopping_android_app.R

class NotificationModel(val context: Context?) {
    fun getNotificationList(): Array<String> {
        return context?.resources?.getStringArray(R.array.notification_array) ?: arrayOf()
    }
}