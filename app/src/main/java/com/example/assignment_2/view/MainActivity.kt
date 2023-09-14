package com.example.assignment_2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.assignment_2.utils.PageAdapter
import com.example.assignment_2.R
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var  tabLayout: TabLayout
    lateinit  var viewPager2: ViewPager2
    private lateinit var adapter: PageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       tabLayout=findViewById(R.id.tabLayout)
        viewPager2=findViewById(R.id.viewPager2)

        adapter = PageAdapter(supportFragmentManager,lifecycle)

        tabLayout.addTab(tabLayout.newTab().setText("Characters"))
        tabLayout.addTab(tabLayout.newTab().setText("Staffs"))
        tabLayout.addTab(tabLayout.newTab().setText("Students"))

        viewPager2.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem=tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

    }
}