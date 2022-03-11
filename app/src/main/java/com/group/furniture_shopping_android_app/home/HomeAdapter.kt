package com.group.furniture_shopping_android_app.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.ProductModel
import com.group.furniture_shopping_android_app.databinding.ItemProductBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
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
}