package com.group.furniture_shopping_android_app.profile

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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
        val menuHost: MenuHost = requireActivity()

        (activity as AppCompatActivity).setSupportActionBar(binding.topAppBarProfile)
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.top_app_bar_profile, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.btn_logout -> {
                        Firebase.auth.signOut()
                        Navigation.findNavController(requireView()).navigate(R.id.action_profileFragment_to_loginSignUpActivity)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        bind()

        with(binding) {
            cardMyOrders.setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_profileFragment_to_myOrdersFragment)
            }
            cardShippingAddresses.setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_profileFragment_to_shippingAddressesFragment)
            }
            cardSettings.setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_profileFragment_to_settingsFragment)
            }
        }

    }

    private fun bind() {
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect {
                if (it is ProfileState.Success) {
                    val shippingQuantity = it.data.shippingAddressesQuantity
                    binding.tvShippingAddressQuantity.text = if (shippingQuantity == 0) {
                        resources.getString(R.string.quantity_shipping_zero)
                    } else resources.getQuantityString(
                        R.plurals.quantity_shipping,
                        shippingQuantity,
                        shippingQuantity
                    )
                    binding.tvProfileName.text = it.data.name
                    binding.tvProfileEmail.text = it.data.email
                }
            }
        }

        with(binding) {
            val ordersQuantity = 0
            val cardsQuantity = 2
            val reviewsQuantity = 2

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