package com.group.furniture_shopping_android_app.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

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

        binding.cardMyOrders.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_profileFragment_to_myOrdersFragment)
        }
        binding.cardShippingAddresses.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_profileFragment_to_shippingAddressesFragment)
        }
    }

    private fun bind() {
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect{
                if (it is ProfileState.Success) {
                    val shippingQuantity = it.data.shippingAddressesQuantity
                     binding.tvShippingAddressQuantity.text = if (shippingQuantity == 0) {
                            resources.getString(R.string.quantity_shipping_zero)
                        } else resources.getQuantityString(
                            R.plurals.quantity_shipping,
                            shippingQuantity,
                            shippingQuantity
                        )
                }
            }
        }

        with(binding) {
            val ordersQuantity = 0
            val cardsQuantity = 2
            val reviewsQuantity = 2


            tvProfileName.text = "Raul Vieira"
            tvProfileEmail.text = "raulguil@gmail.com"

            tvMyOrdersQuantity.text = if (ordersQuantity == 0) {
                resources.getString(R.string.quantity_orders_zero)
            } else resources.getQuantityString(
                R.plurals.quantity_orders,
                ordersQuantity,
                ordersQuantity
            )


            tvCardsQuantity.text = if (cardsQuantity == 0) {
                resources.getString(R.string.quantity_cards_zero)
            } else resources.getQuantityString(
                R.plurals.quantity_cards,
                cardsQuantity,
                cardsQuantity
            )
            tvReviewsQuantity.text = if (reviewsQuantity == 0) {
                resources.getString(R.string.quantity_reviews_zero)
            } else resources.getQuantityString(
                R.plurals.quantity_reviews,
                reviewsQuantity,
                reviewsQuantity
            )
        }
    }
}