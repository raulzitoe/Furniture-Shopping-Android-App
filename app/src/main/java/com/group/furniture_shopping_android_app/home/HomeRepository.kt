package com.group.furniture_shopping_android_app.home

import com.google.firebase.database.*
import com.group.furniture_shopping_android_app.ProductListModel
import com.group.furniture_shopping_android_app.ProductModel
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class HomeRepository {

    private lateinit var database: DatabaseReference

    suspend fun getProductList(): ArrayList<ProductModel> {

        return suspendCoroutine { cont ->
            database = FirebaseDatabase.getInstance().reference
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.getValue(ProductListModel::class.java)?.let { value ->
                        cont.resume(value.productList)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    cont.resumeWithException(Throwable("Exception"))
                }
            })
        }
    }
}