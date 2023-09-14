package com.example.assignment_2.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.assignment_2.fragment.FragmentAllList
import com.example.assignment_2.fragment.FragmentStaffList
import com.example.assignment_2.fragment.FragmentStudentList

class PageAdapter(fragmentManager: FragmentManager , lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return  3
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            FragmentAllList()
        } else if (position == 1) {
            FragmentStaffList()
        } else {
            FragmentStudentList()
        }

    }
}