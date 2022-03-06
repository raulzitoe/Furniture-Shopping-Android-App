package com.group.furniture_shopping_android_app.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            val ordersQuantity = 10
            val shippingQuantity = 1
            val cardsQuantity = 2
            val reviewsQuantity = 2

            tvMyOrdersQuantity.text = if (ordersQuantity == 0) {
                resources.getString(R.string.quantity_orders_zero)
            }
            else resources.getQuantityString(R.plurals.quantity_orders, ordersQuantity, ordersQuantity)

            tvShippingAddressQuantity.text = if (shippingQuantity == 0) {
                resources.getString(R.string.quantity_shipping_zero)
            }
            else resources.getQuantityString(R.plurals.quantity_shipping, shippingQuantity, shippingQuantity)
            tvCardsQuantity.text = if (cardsQuantity == 0) {
                resources.getString(R.string.quantity_cards_zero)
            }
            else resources.getQuantityString(R.plurals.quantity_cards, shippingQuantity, shippingQuantity)
            tvReviewsQuantity.text = if (reviewsQuantity == 0) {
                resources.getString(R.string.quantity_reviews_zero)
            }
            else resources.getQuantityString(R.plurals.quantity_reviews, shippingQuantity, shippingQuantity)
        }
    }
}