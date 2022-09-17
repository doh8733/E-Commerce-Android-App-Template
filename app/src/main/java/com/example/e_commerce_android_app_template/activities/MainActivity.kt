package com.example.e_commerce_android_app_template.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.e_commerce_android_app_template.R
import com.example.e_commerce_android_app_template.adapter.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val vpView: ViewPager by lazy { findViewById<ViewPager>(R.id.vpView) }
    private val bottmNav: BottomNavigationView by lazy { findViewById<BottomNavigationView>(R.id.bottm_nav) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewPager()
        bottmNav.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.action_shop ->{
                    vpView.currentItem = 0
                }
                R.id.action_bag ->{
                    vpView.currentItem = 1
                }
                R.id.action_trade ->{
                    vpView.currentItem = 2
                }
            }

            return@setOnNavigationItemSelectedListener true
        }
    }
    private fun setUpViewPager() {

        vpView.adapter = ViewPagerAdapter(
            supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        vpView.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        bottmNav.menu.findItem(R.id.action_shop).isChecked = true
                    }
                    1 -> {
                        bottmNav.menu.findItem(R.id.action_bag).isChecked = true
                    }
                    2 -> {
                        bottmNav.menu.findItem(R.id.action_trade).isChecked = true
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }
}