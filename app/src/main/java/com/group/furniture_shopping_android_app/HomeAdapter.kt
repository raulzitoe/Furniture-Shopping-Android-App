package com.group.furniture_shopping_android_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.databinding.ItemProductBinding

class HomeAdapter (private val productList: Array<String>) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private lateinit var binding: ItemProductBinding

    class HomeViewHolder(val binding: ItemProductBinding, val productList: Array<String>) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.productName.text = productList[position]
            binding.productPrice.text = "12.00"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding, productList)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(position)
    }


    override fun getItemCount(): Int {
        return productList.size
    }

}