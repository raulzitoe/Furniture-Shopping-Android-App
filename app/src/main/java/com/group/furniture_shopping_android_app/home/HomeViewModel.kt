package com.group.furniture_shopping_android_app.home

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.group.furniture_shopping_android_app.ProductListModel
import com.group.furniture_shopping_android_app.ProductModel
import com.group.furniture_shopping_android_app.R


class HomeViewModel(val context: Context?) : ViewModel() {

    private val _viewState: MutableLiveData<HomeViewState> = MutableLiveData()
    val viewState: LiveData<HomeViewState> = _viewState
    private lateinit var database: DatabaseReference

    init {
        getProductList()
    }

    fun getProductList() {
        database = FirebaseDatabase.getInstance().reference
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.getValue(ProductListModel::class.java)?.let { value ->
                    _viewState.value = HomeViewState.Success(value.productList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })
    }
}
