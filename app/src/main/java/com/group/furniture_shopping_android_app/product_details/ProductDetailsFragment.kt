package com.group.furniture_shopping_android_app.product_details

import android.app.Activity
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.group.furniture_shopping_android_app.MainActivity
import com.group.furniture_shopping_android_app.R
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
                    val reviewsCount = viewState.data.reviewsCount
                    with(binding){
                        fragmentProductName.text = viewState.data.name
                        fragmentProductPrice.text = viewState.data.price
                        fragmentProductRating.text = viewState.data.rating.toString()

                        fragmentProductReviewsCount.text = if (reviewsCount == 0) {
                            resources.getString(R.string.product_info_quantity_reviews_zero, reviewsCount)
                        }
                        else resources.getQuantityString(R.plurals.product_info_quantity_reviews, reviewsCount, reviewsCount)
                        fragmentProductDescription.text = viewState.data.description
                    }
                    val bitmap = BitmapFactory.decodeFile(viewState.data.localImagePath)
                    binding.fragmentProductImage.setImageBitmap(bitmap)

                }
            }
        }
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)

        binding.btnBack.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.btnMinus.setOnClickListener {
            val value = binding.fragmentProductQuantity.text.toString().toInt()
            binding.fragmentProductQuantity.text = if(value > 0) (value - 1).toString() else 0.toString()
        }

        binding.btnPlus.setOnClickListener {
            val value = binding.fragmentProductQuantity.text.toString().toInt()
            binding.fragmentProductQuantity.text = (value + 1).toString()
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).setBottomNavigationVisibility(View.VISIBLE)
    }
}