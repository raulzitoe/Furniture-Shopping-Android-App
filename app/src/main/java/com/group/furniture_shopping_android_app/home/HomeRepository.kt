package com.group.furniture_shopping_android_app.home

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.reflect.TypeToken
import com.group.furniture_shopping_android_app.ProductListModel
import com.group.furniture_shopping_android_app.ProductModel
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class HomeRepository {

    private lateinit var database: DatabaseReference

    suspend fun getProductList(): ProductListModel {
        database = Firebase.database.reference.child("products")
        return suspendCoroutine { cont ->
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.getValue(ProductListModel::class.java)?.let { value ->
                        cont.resume(value)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
//                    cont.resumeWithException(Throwable("Exception"))
                }
            })
        }
    }
}