package com.group.furniture_shopping_android_app.my_orders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.ItemMyOrdersBinding

class MyOrdersAdapter(private val orderStatus: String, val orderStatusTypes: Array<String>, val context: Context) :
    RecyclerView.Adapter<MyOrdersAdapter.MyOrdersViewHolder>() {
    private lateinit var binding: ItemMyOrdersBinding
    var myOrdersList: List<MyOrdersModel> = listOf()
        set(value) {
            field = value.filter { it.order_status == orderStatus }
        }

    inner class MyOrdersViewHolder(val binding: ItemMyOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val myOrder = myOrdersList[position]
            binding.orderNumber.text = myOrder.order_number.toString()
            binding.orderDate.text = myOrder.order_date
            binding.orderQuantity.text = myOrder.order_quantity.toString()
            binding.orderTotalAmount.text = myOrder.order_total_amount.toString()
            binding.orderStatus.text = myOrder.order_status
            binding.orderStatus.setTextColor(
                when (orderStatus) {
                    orderStatusTypes[0] -> ContextCompat.getColor(context, R.color.green)
                    orderStatusTypes[1] -> ContextCompat.getColor(context, R.color.yellow)
                    orderStatusTypes[2] -> ContextCompat.getColor(context, R.color.red)
                    else -> 0
                }
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrdersViewHolder {
        binding = ItemMyOrdersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyOrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyOrdersViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return myOrdersList.size
    }

}