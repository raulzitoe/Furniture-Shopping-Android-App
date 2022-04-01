package com.group.furniture_shopping_android_app.my_orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.group.furniture_shopping_android_app.databinding.FragmentMyOrdersBinding


class MyOrdersFragment : Fragment() {
    private lateinit var binding: FragmentMyOrdersBinding
    private lateinit var myOrdersViewPagerAdapter: MyOrdersViewPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyOrdersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout = binding.myOrdersTabLayout

        myOrdersViewPagerAdapter = MyOrdersViewPagerAdapter(this)
        viewPager = binding.myOrdersViewpager
        viewPager.adapter = myOrdersViewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()
    }
}