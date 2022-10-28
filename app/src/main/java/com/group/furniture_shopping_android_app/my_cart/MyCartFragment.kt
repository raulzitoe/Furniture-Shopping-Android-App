package com.group.furniture_shopping_android_app.my_cart

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.group.furniture_shopping_android_app.MainActivity
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.FragmentMyCartBinding
import com.group.furniture_shopping_android_app.repository.CartModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MyCartFragment : Fragment() {

    private lateinit var binding: FragmentMyCartBinding
    private val viewModel: MyCartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyCartBinding.inflate(layoutInflater)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myActivity = (activity as AppCompatActivity)

        myActivity.setSupportActionBar(binding.topAppBarMyCart)
        myActivity.supportActionBar?.let {
            it.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        with(binding) {
            recyclerMyCart.adapter =
                MyCartAdapter(listener = object : MyCartAdapter.CartRecyclerListener {
                    override fun removeItemFromCart(cartItem: CartModel) {
                        viewModel.removeItemFromCart(cartItem)
                    }

                    override fun updateItem(cartItem: CartModel) {
                        viewModel.updateItemFromCart(cartItem)
                    }
                })

            topAppBarMyCart.setNavigationOnClickListener {
                Navigation.findNavController(view).navigateUp()
            }

            btnCheckout.setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_myCartFragment_to_checkoutFragment)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is MyCartViewState.Success -> (binding.recyclerMyCart.adapter as MyCartAdapter).submitList(
                            it.myCartList
                        )
                        is MyCartViewState.Error -> throw Exception("Failed to load My Cart")
                        is MyCartViewState.Loading -> {}
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).setBottomNavigationVisibility(View.VISIBLE)
    }
}