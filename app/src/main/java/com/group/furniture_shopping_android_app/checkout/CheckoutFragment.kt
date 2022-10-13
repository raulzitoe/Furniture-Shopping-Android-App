package com.group.furniture_shopping_android_app.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.group.furniture_shopping_android_app.MainActivity
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.FragmentCheckoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutFragment : Fragment() {
    private lateinit var binding: FragmentCheckoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckoutBinding.inflate(layoutInflater)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myActivity = activity as AppCompatActivity

        myActivity.setSupportActionBar(binding.toolbarCheckout)
        myActivity.supportActionBar?.let {
            it.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        binding.toolbarCheckout.setNavigationOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).setBottomNavigationVisibility(View.VISIBLE)
    }
}