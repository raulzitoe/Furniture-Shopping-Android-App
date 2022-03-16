package com.group.furniture_shopping_android_app.product_details

import android.app.Activity
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.group.furniture_shopping_android_app.MainActivity
import com.group.furniture_shopping_android_app.databinding.FragmentProductBinding

class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding
    val args: ProductDetailsFragmentArgs by navArgs()
    private val viewModel: ProductDetailsViewModel by viewModels {
        ProductDetailsViewModelFactory(args.productId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setBottomNavigationVisibility(View.GONE)
        val viewStateObserver = Observer<ProductDetailsViewState> { viewState ->
            when(viewState){
                is ProductDetailsViewState.Success -> {
                    with(binding){
                        fragmentProductName.text = viewState.data.name
                        fragmentProductPrice.text = viewState.data.price
                        fragmentProductRating.text = viewState.data.rating.toString()
                        fragmentProductReviewsCount.text = viewState.data.reviewsCount.toString()
                        fragmentProductDescription.text = viewState.data.description
                    }
                    val bitmap = BitmapFactory.decodeFile(viewState.data.localImagePath)
                    binding.fragmentProductImage.setImageBitmap(bitmap)

                }
            }
        }
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)

    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).setBottomNavigationVisibility(View.VISIBLE)
    }
}