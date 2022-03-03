package com.group.furniture_shopping_android_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.group.furniture_shopping_android_app.databinding.FragmentHomeBinding
import com.group.furniture_shopping_android_app.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    private fun bind() {
        with (binding) {
            tvProfileName.text = "Raul Vieira"
            tvProfileEmail.text = "raulguil@gmail.com"
            val orders_quantity = 10
            val shipping_quantity = 1
            val cards_quantity = 2
            val reviews_quantity = 2

            tvMyOrdersQuantity.text = if (orders_quantity == 0) {
                resources.getString(R.string.quantity_orders_zero)
            }
            else resources.getQuantityString(R.plurals.quantity_orders, orders_quantity, orders_quantity)

            tvShippingAddressQuantity.text = if (shipping_quantity == 0) {
                resources.getString(R.string.quantity_shipping_zero)
            }
            else resources.getQuantityString(R.plurals.quantity_shipping, shipping_quantity, shipping_quantity)
            tvCardsQuantity.text = if (cards_quantity == 0) {
                resources.getString(R.string.quantity_cards_zero)
            }
            else resources.getQuantityString(R.plurals.quantity_cards, shipping_quantity, shipping_quantity)
            tvReviewsQuantity.text = if (reviews_quantity == 0) {
                resources.getString(R.string.quantity_reviews_zero)
            }
            else resources.getQuantityString(R.plurals.quantity_reviews, shipping_quantity, shipping_quantity)
        }
    }
}