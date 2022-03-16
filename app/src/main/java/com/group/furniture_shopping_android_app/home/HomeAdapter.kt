package com.group.furniture_shopping_android_app.home

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.group.furniture_shopping_android_app.ProductModel
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.ItemProductBinding
import java.io.File

class HomeAdapter (val listener: HomeItemListener) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private lateinit var binding: ItemProductBinding
    var productList: ArrayList<ProductModel> = arrayListOf()
        set(value) {
            field = value
            productFilteredList = value
        }
    private var productFilteredList: ArrayList<ProductModel> = arrayListOf()


    inner class HomeViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val product = productFilteredList[position]
            binding.productName.text = product.name
            binding.productPrice.text = product.price
            binding.root.setOnClickListener { listener.itemClick(product.id) }
            binding.productImage.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.black))
            binding.productImage.setImageBitmap(null)
            val storageRef = FirebaseStorage.getInstance().reference.child(product.image)

            val localFile = File.createTempFile("tempImage","png")
            storageRef.getFile(localFile).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                binding.productImage.setImageBitmap(bitmap)
            }.addOnFailureListener {
                //Failed to load image
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return productFilteredList.size
    }

    fun setFilters(productFilterList: ArrayList<HomeFilter>) {
        productFilteredList = arrayListOf()

        if (productFilterList.isEmpty()) {
            productFilteredList = productList
            return
        }

        productFilteredList = productList.filter {
            it.category.toUpperCase() in productFilterList.toString()
        } as ArrayList<ProductModel>
    }

    interface HomeItemListener {
        fun itemClick(id: Int)
    }
}