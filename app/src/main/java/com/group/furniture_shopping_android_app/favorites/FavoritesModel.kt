package com.group.furniture_shopping_android_app.favorites

import android.content.Context
import com.group.furniture_shopping_android_app.R

class FavoritesModel(val context: Context?) {
    fun getFavoritesList(): Array<String> {
        return context?.resources?.getStringArray(R.array.favorites_array) ?: arrayOf()
    }
}