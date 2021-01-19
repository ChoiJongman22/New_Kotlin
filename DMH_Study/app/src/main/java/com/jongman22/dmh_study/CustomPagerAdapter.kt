package com.jongman22.dmh_study

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.jongman22.dmh_study.databinding.ItemBinding

class CustomPagerAdapter(val context: Context, val images: IntArray) : PagerAdapter() {

    override fun getCount(): Int = images.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemBinding.inflate(LayoutInflater.from(context), container, false)
        binding.imageView.setImageResource(images[position])
        container?.addView(binding.root)
        return binding.root
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}