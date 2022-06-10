package com.group.furniture_shopping_android_app.product_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.group.furniture_shopping_android_app.ProductModel
import java.io.File

class ProductDetailsViewModel(private val productId: Int) : ViewModel () {
    val viewState: MutableLiveData<ProductDetailsViewState> = MutableLiveData()
    private lateinit var database: DatabaseReference

    init {
        getProductInfo()
    }

    private fun getProductInfo() {
        database = FirebaseDatabase.getInstance().reference.child("productInfo").child(productId.toString())
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.getValue(ProductModel::class.java)?.let { value ->
                    setLocalImage(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })
    }
    private fun setLocalImage(product: ProductModel){
        val storageRef = FirebaseStorage.getInstance().reference.child(product.image)
        val localFile = File.createTempFile("tempImage","png")
        storageRef.getFile(localFile).addOnSuccessListener {
            product.localImagePath = localFile.absolutePath
            viewState.value = ProductDetailsViewState.Success(product)
        }.addOnFailureListener {
            //Failed to load image
        }
    }
}