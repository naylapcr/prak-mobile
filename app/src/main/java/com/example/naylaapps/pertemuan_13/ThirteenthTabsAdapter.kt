package com.example.naylaapps.pertemuan_13

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.naylaapps.Home.pertemuan_10.TabAFragment
import com.example.naylaapps.Home.pertemuan_10.TabBFragment
import com.example.naylaapps.Home.pertemuan_10.TabCFragment

class ThirteenthTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabCaptureFragment()
            1 -> TabQrcodeFragment()
            2 -> TabScanFragment ()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}
