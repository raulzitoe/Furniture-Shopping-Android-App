package com.group.furniture_shopping_android_app.my_orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.databinding.ItemMyOrdersBinding

class MyOrdersAdapter : RecyclerView.Adapter<MyOrdersAdapter.MyOrdersViewHolder>() {
    private lateinit var binding: ItemMyOrdersBinding
    var myOrdersList: List<MyOrdersModel> = listOf()

    inner class MyOrdersViewHolder(val binding: ItemMyOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val myOrder = myOrdersList[position]
            binding.orderNumber.text = myOrder.order_number.toString()
            binding.orderDate.text = myOrder.order_date
            binding.orderQuantity.text = myOrder.order_quantity.toString()
            binding.orderTotalAmount.text = myOrder.order_total_amount.toString()
            binding.orderStatus.text = myOrder.order_status
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrdersViewHolder {
        binding = ItemMyOrdersBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return MyOrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyOrdersViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return myOrdersList.size
    }

}