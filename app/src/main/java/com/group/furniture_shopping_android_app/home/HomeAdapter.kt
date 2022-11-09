package com.group.furniture_shopping_android_app.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import com.group.furniture_shopping_android_app.ProductListModel
import com.group.furniture_shopping_android_app.ProductModel
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.ItemProductBinding
import io.github.rosariopfernandes.firecoil.load
import java.util.*
import kotlin.collections.ArrayList

class HomeAdapter (val listener: HomeItemListener) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private lateinit var binding: ItemProductBinding
    var productList: ProductListModel = ProductListModel(arrayListOf())
        set(value) {
            field = value
            productFilteredList = ProductListModel(value.products)
        }
    private var productFilteredList: ProductListModel = ProductListModel(arrayListOf())


    inner class HomeViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val product = productFilteredList.products[position]
            binding.productName.text = product.name
            binding.productPrice.text = product.price
            binding.root.setOnClickListener { listener.itemClick(product.id) }
            binding.productImage.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.primary))
            binding.productImage.setImageBitmap(null)
            val storageRef = FirebaseStorage.getInstance().reference.child(product.image)
            binding.productImage.load(storageRef)
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
        return productFilteredList.products.size
    }

    fun setFilters(productFilterList: ArrayList<HomeFilter>) {
        productFilteredList = ProductListModel(arrayListOf())

        if (productFilterList.isEmpty()) {
            productFilteredList = productList
            return
        }

        productFilteredList.products = productList.products.filter {
            it.category.uppercase(Locale.getDefault()) in productFilterList.toString()
        } as ArrayList<ProductModel>
    }

    interface HomeItemListener {
        fun itemClick(id: Int)
    }
}