package com.example.e_commerce_android_app_template.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.e_commerce_android_app_template.fragment.BagFragment
import com.example.e_commerce_android_app_template.fragment.ShopFragment
import com.example.e_commerce_android_app_template.fragment.ShopertinoFragment

class ViewPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
      return  when (position) {
            0 -> return ShopFragment()

            1 -> return BagFragment()

            2 -> return ShopertinoFragment()

            else -> ShopFragment()
        }
    }
}