package com.group.furniture_shopping_android_app.shipping_addresses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.ItemShippingAddressesBinding
import com.group.furniture_shopping_android_app.repository.ShippingAddressModel

class ShippingAddressesAdapter(val listener: ShippingRecyclerListener) :
    ListAdapter<ShippingAddressModel, ShippingAddressesAdapter.ShippingAddressesViewHolder>(
        AsyncDifferConfig.Builder(DiffCallback()).build()
    ) {
    private lateinit var binding: ItemShippingAddressesBinding

    private class DiffCallback : DiffUtil.ItemCallback<ShippingAddressModel>() {
        override fun areItemsTheSame(
            oldItem: ShippingAddressModel,
            newItem: ShippingAddressModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ShippingAddressModel,
            newItem: ShippingAddressModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class ShippingAddressesViewHolder(val binding: ItemShippingAddressesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = currentList[position]
            binding.shippingName.text = item.name
            binding.shippingAddress.apply {
                text = context.getString(
                    R.string.full_address,
                    item.street,
                    item.zip,
                    item.city,
                    item.province,
                    item.country
                )
            }
            binding.btnEditAddress.setOnClickListener {
                listener.clickUpdateButton(item)
            }
            binding.checkboxUseAddress.isChecked = item.isDefaultAddress
            binding.checkboxUseAddress.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    listener.clickSetDefaultAddressButton(item)
                }
            }
        }
    }

    interface ShippingRecyclerListener {
        fun clickUpdateButton(item: ShippingAddressModel)
        fun clickSetDefaultAddressButton(item: ShippingAddressModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShippingAddressesViewHolder {
        binding =
            ItemShippingAddressesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShippingAddressesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShippingAddressesViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}