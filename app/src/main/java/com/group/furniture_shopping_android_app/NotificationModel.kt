package com.group.furniture_shopping_android_app

import android.content.Context

class NotificationModel(val context: Context?) {
    fun getNotificationList(): Array<String> {
        return context?.resources?.getStringArray(R.array.notification_array) ?: arrayOf()
    }
}