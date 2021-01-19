package com.example.diaryinmyhand

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.diaryinmyhand.databinding.ImageItemBinding

class ImagerAdpater(fm:FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val items=ArrayList<Fragment>()
    // position 위치의 프래그먼트
    override fun getItem(position: Int): Fragment {
        return items[position]
    }
    // 프래그먼트 개수
    override fun getCount(): Int {
        return items.size
    }
    // 프래그먼트 갱신
    fun updateFragments(items:List<Fragment>){
        this.items.addAll(items)
    }
}